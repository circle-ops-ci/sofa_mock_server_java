package com.cybavo.sofa.mock;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Optional;
import java.util.logging.Logger;

import com.cybavo.sofa.api.BaseResponse;
import com.cybavo.sofa.api.CallbackStruct;
import com.cybavo.sofa.api.CommonResponse;
import com.cybavo.sofa.api.SetApiTokenRequest;
import com.cybavo.sofa.api.WithdrawTransaction;
import com.cybavo.sofa.mock.entity.ApiToken;
import com.cybavo.sofa.mock.repository.ApiTokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {
	private static Logger logger = Logger.getLogger(MockController.class.getName());

	@Autowired
	Api apiClient;

	@Autowired
	ApiTokenRepository repository;

	@PostMapping("/v1/mock/wallets/{walletId}/apitoken")
	public HttpEntity<CommonResponse> setAPIToken(@PathVariable("walletId") long walletId,
			@RequestBody SetApiTokenRequest request) {

		ApiToken newToken = new ApiToken(walletId, request.getApiCode(), request.getApiSecret());
		repository.save(newToken);

		return new ResponseEntity<>(new CommonResponse(1L), HttpStatus.OK);
	}

	@PostMapping("/v1/mock/wallets/{walletId}/addresses")
	public HttpEntity<String> createDepositWalletAddresses(@PathVariable("walletId") long walletId,
			@RequestBody String request) {

		Api.Response response = apiClient.makeRequest(walletId, "POST",
				String.format("/v1/sofa/wallets/%d/addresses", walletId), null, request);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@GetMapping("/v1/mock/wallets/{walletId}/addresses")
	public HttpEntity<String> getDepositWalletAddresses(@PathVariable("walletId") long walletId,
			@RequestParam(name = "start_index", defaultValue = "0") Integer startIndex,
			@RequestParam(name = "request_number", defaultValue = "0") Integer requestNumber) {

		Api.Response response = apiClient.makeRequest(walletId, "GET",
				String.format("/v1/sofa/wallets/%d/addresses", walletId),
				new String[] { String.format("start_index=%d", startIndex),
						String.format("request_number=%d", requestNumber), },
				null);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@GetMapping("/v1/mock/wallets/{walletId}/pooladdress")
	public HttpEntity<String> getDepositWalletPoolAddresses(@PathVariable("walletId") long walletId) {

		Api.Response response = apiClient.makeRequest(walletId, "GET",
				String.format("/v1/sofa/wallets/%d/pooladdress", walletId), null, null);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@PostMapping("/v1/mock/wallets/callback")
	public HttpEntity<Object> callback(@RequestHeader("X-CHECKSUM") String checkSum, @RequestBody String bodyString) {
		try {

			final ObjectMapper mapper = new ObjectMapper();
			CallbackStruct request = mapper.readValue(bodyString, CallbackStruct.class);

			Optional<ApiToken> optApiToken = repository.findById(request.getWalletId());
			String payload = bodyString + optApiToken.get().getApiSecret();

			// Generate check sum
			final MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(payload.getBytes("utf8"));
			final Base64.Encoder encoder = Base64.getUrlEncoder();
			final String checksumVerf = encoder.encodeToString(digest.digest());

			if (!checksumVerf.equals(checkSum)) {
				logger.info(String.format("callback expect checkSum %s, got %s", checksumVerf, checkSum));
				throw new Exception("Bad checksum");
			}

			logger.info(String.format("callback %s", bodyString));
			return new ResponseEntity<>("OK", HttpStatus.OK);
		} catch (Exception e) {
			logger.warning(String.format("callback failed with exception %s", e.toString()));
			return new ResponseEntity<>(new BaseResponse(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/v1/mock/wallets/withdrawal/callback")
	public HttpEntity<Object> withdrawalCallback(@RequestHeader("X-CHECKSUM") String checkSum,
			@RequestBody String bodyString) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			WithdrawTransaction.Request request = mapper.readValue(bodyString, WithdrawTransaction.Request.class);

			// How to verify:
			// 1. Try to find corresponding API secret by request.Requests[0].orderID
			// 2. Calculate checksum then compare to X-CHECKSUM header (refer to sample code
			// bellow)
			// 3. If these two checksums match and the request is valid in your system,
			// reply 200, "OK" otherwise reply 400 to decline the withdrawal

			// sample code to calculate checksum and verify
			// final MessageDigest digest = MessageDigest.getInstance("SHA-256");
			// digest.reset();
			// digest.update(payload.getBytes("utf8"));
			// final Base64.Encoder encoder = Base64.getUrlEncoder();
			// final String checksumVerf = encoder.encodeToString(digest.digest());

			// if (!checksumVerf.equals(checkSum)) {
			// logger.info(String.format("callback expect checkSum %s, got %s",
			// checksumVerf, checkSum));
			// throw new Exception("Bad checksum");
			// }

			logger.info(String.format("Withdraw Callback %s", bodyString));
			return new ResponseEntity<>("OK", HttpStatus.OK);
		} catch (Exception e) {
			logger.warning(String.format("callback failed with exception %s", e.toString()));
			return new ResponseEntity<>(new BaseResponse(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/v1/mock/wallets/{walletId}/callback/resend")
	public HttpEntity<String> callbackResend(@PathVariable("walletId") long walletId,
			@RequestBody String request) {
		try {
			Api.Response response = apiClient.makeRequest(walletId, "POST",
					String.format("/v1/sofa/wallets/%d/collection/notifications/manual", walletId), null, request);

			return new ResponseEntity<String>(response.getContent(), response.getStatus());

		} catch (Exception e) {
			logger.warning(String.format("callbackResend of wallet %d failed %s", walletId, e.toString()));
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/v1/mock/wallets/{walletId}/withdraw")
	public HttpEntity<String> withdrawTransactions(@PathVariable("walletId") long walletId,
			@RequestBody String request) {

		Api.Response response = apiClient.makeRequest(walletId, "POST",
				String.format("/v1/sofa/wallets/%d/sender/transactions", walletId), null, request);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@PostMapping("/v1/mock/wallets/{walletId}/sender/transactions/{orderId}/cancel")
	public HttpEntity<String> cancelWithdrawTransactions(@PathVariable("walletId") long walletId,
			@PathVariable("orderId") String orderId, @RequestBody String request) {
		Api.Response response = apiClient.makeRequest(walletId, "POST",
				String.format("/v1/sofa/wallets/%d/sender/transactions/%s/cancel", walletId, orderId), null, null);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@GetMapping("/v1/mock/wallets/{walletId}/sender/transactions/{orderId}")
	public HttpEntity<String> getWithdrawTransactionState(@PathVariable("walletId") long walletId,
			@PathVariable("orderId") String orderId) {
		Api.Response response = apiClient.makeRequest(walletId, "GET",
				String.format("/v1/sofa/wallets/%d/sender/transactions/%s", walletId, orderId), null, null);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@GetMapping("/v1/mock/wallets/{walletId}/sender/balance")
	public HttpEntity<String> getWithdrawalWalletBalance(@PathVariable("walletId") long walletId) {
		Api.Response response = apiClient.makeRequest(walletId, "GET",
				String.format("/v1/sofa/wallets/%d/sender/balance", walletId), null, null);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@GetMapping("/v1/mock/wallets/{walletId}/apisecret")
	public HttpEntity<String> getTxAPITokenStatus(@PathVariable("walletId") long walletId) {
		Api.Response response = apiClient.makeRequest(walletId, "GET",
				String.format("/v1/sofa/wallets/%d/apisecret", walletId), null, null);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@GetMapping("/v1/mock/wallets/{walletId}/notifications")
	public HttpEntity<String> getNotifications(@PathVariable("walletId") Long walletId,
			@RequestParam("from_time") Long fromTime, @RequestParam("to_time") Long toTime,
			@RequestParam("type") Integer type) {

		Api.Response response = apiClient.makeRequest(walletId, "GET",
				String.format("/v1/sofa/wallets/%d/notifications", walletId),
				new String[] { String.format("from_time=%d", fromTime), String.format("to_time=%d", toTime),
						String.format("type=%d", type) },
				null);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@PostMapping("/v1/mock/wallets/{walletId}/notifications/get_by_id")
	public HttpEntity<String> getNotificationsById(@PathVariable("walletId") Long walletId,
			@RequestBody String request) {

		Api.Response response = apiClient.makeRequest(walletId, "POST",
				String.format("/v1/sofa/wallets/%d/notifications/get_by_id", walletId), null, request);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@GetMapping("/v1/mock/wallets/{walletId}/receiver/notifications/txid/{txId}/{voutIndex}")
	public HttpEntity<String> getDepositCallback(@PathVariable("walletId") Long walletId,
			@PathVariable("txId") String txId, @PathVariable("voutIndex") Integer voutIndex) {

		Api.Response response = apiClient.makeRequest(walletId, "GET",
				String.format("/v1/sofa/wallets/%d/receiver/notifications/txid/%s/%d", walletId, txId, voutIndex), null, null);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@GetMapping("/v1/mock/wallets/{walletId}/sender/notifications/order_id/{orderId}")
	public HttpEntity<String> getWithdrawalCallback(@PathVariable("walletId") Long walletId,
			@PathVariable("orderId") String orderId) {

		Api.Response response = apiClient.makeRequest(walletId, "GET",
				String.format("/v1/sofa/wallets/%d/sender/notifications/order_id/%s", walletId, orderId), null, null);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@GetMapping("/v1/mock/wallets/{walletId}/transactions")
	public HttpEntity<String> getTransactionHistory(@PathVariable("walletId") long walletId,
			@RequestParam("from_time") Long fromTime, @RequestParam("to_time") Long toTime,
			@RequestParam(name = "start_index", defaultValue = "0") Integer startIndex,
			@RequestParam(name = "request_number", defaultValue = "0") Integer requestNumber,
			@RequestParam(name = "state", defaultValue = "-1") Integer state) {

		Api.Response response = apiClient.makeRequest(walletId, "GET",
				String.format("/v1/sofa/wallets/%d/transactions", walletId),
				new String[] { String.format("from_time=%d", fromTime), String.format("to_time=%d", toTime),
						String.format("start_index=%d", startIndex), String.format("request_number=%d", requestNumber),
						String.format("state=%d", state) },
				null);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@GetMapping("/v1/mock/wallets/{walletId}/blocks")
	public HttpEntity<String> getWalletBlockInfo(@PathVariable("walletId") long walletId) {

		Api.Response response = apiClient.makeRequest(walletId, "GET",
				String.format("/v1/sofa/wallets/%d/blocks", walletId), null, null);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@GetMapping("/v1/mock/wallets/{walletId}/addresses/invalid-deposit")
	public HttpEntity<String> getInvalidDepositAddresses(@PathVariable("walletId") long walletId) {

		Api.Response response = apiClient.makeRequest(walletId, "GET",
				String.format("/v1/sofa/wallets/%d/addresses/invalid-deposit", walletId), null, null);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@GetMapping("/v1/mock/wallets/{walletId}/info")
	public HttpEntity<String> getWalletInfo(@PathVariable("walletId") long walletId) {

		Api.Response response = apiClient.makeRequest(walletId, "GET",
				String.format("/v1/sofa/wallets/%d/info", walletId), null, null);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@PostMapping("/v1/mock/wallets/{walletId}/addresses/verify")
	public HttpEntity<String> verifyAddresses(@PathVariable("walletId") long walletId,
			@RequestBody String request) {

		Api.Response response = apiClient.makeRequest(walletId, "POST",
				String.format("/v1/sofa/wallets/%d/addresses/verify", walletId), null, request);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}

	@PostMapping("/v1/mock/wallets/{walletId}/autofee")
	public HttpEntity<String> getAutoFee(@PathVariable("walletId") long walletId,
			@RequestBody String request) {

		Api.Response response = apiClient.makeRequest(walletId, "POST",
				String.format("/v1/sofa/wallets/%d/autofee", walletId), null, request);

		return new ResponseEntity<String>(response.getContent(), response.getStatus());
	}
}
