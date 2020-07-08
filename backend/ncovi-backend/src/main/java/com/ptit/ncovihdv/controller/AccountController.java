package com.ptit.ncovihdv.controller;

import com.ptit.ncovihdv.dto.request.*;
import com.ptit.ncovihdv.dto.response.*;
import com.ptit.ncovihdv.exception.ApplicationException;
import com.ptit.ncovihdv.service.AccountService;
import com.ptit.ncovihdv.util.message.ApplicationCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Log4j2
@Controller
public class AccountController {

	@Autowired
	AccountService accountService;

	@PostMapping("/login")
	public ResponseEntity<?> loginByUsername(@RequestBody LoginByUsernameRequest request) {
		BaseResponseData baseResponseData = new BaseResponseData();
		try {
			if (!request.isValid()) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.REQUEST_ERROR_MSG);
			}
			LoginResponse response = accountService.loginByUsernameAction(request);
			if (response != null) {
				baseResponseData.setErrorCode(ApplicationCode.SUCCESS);
				baseResponseData.setMessage(ApplicationCode.SUCCESS_MSG);
				baseResponseData.setData(response);
			} else {
				baseResponseData.setErrorCode(ApplicationCode.ERROR);
				baseResponseData.setMessage(ApplicationCode.PASSWORD_WRONG);
				baseResponseData.setData(response);
			}

		} catch (ApplicationException e) {
			baseResponseData.setErrorCode(e.getCode());
			baseResponseData.setMessage(e.getMessage());
		} finally {

		}
		return new ResponseEntity<>(baseResponseData, HttpStatus.OK);
	}

	@PostMapping("/loginFacebook")
	public ResponseEntity<?> loginFacebook(@RequestBody LoginFacebookRequest request) {
		BaseResponseData baseResponseData = new BaseResponseData();
		try {
			if (!request.isValid()) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.REQUEST_ERROR_MSG);
			}
			LoginResponse response = accountService.loginFacebookAction(request);

			baseResponseData.setErrorCode(ApplicationCode.SUCCESS);
			baseResponseData.setMessage(ApplicationCode.SUCCESS_MSG);
			baseResponseData.setData(response);

		} catch (ApplicationException e) {
			baseResponseData.setErrorCode(e.getCode());
			baseResponseData.setMessage(e.getMessage());
		} finally {

		}
		return new ResponseEntity<>(baseResponseData, HttpStatus.OK);
	}

	@PostMapping("/loginGoogle")
	public ResponseEntity<?> loginGoogle(@RequestBody LoginGoogleRequest request) {
		BaseResponseData baseResponseData = new BaseResponseData();
		try {
			if (!request.isValid()) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.REQUEST_ERROR_MSG);
			}
			LoginResponse response = accountService.loginGoogleAction(request);

			baseResponseData.setErrorCode(ApplicationCode.SUCCESS);
			baseResponseData.setMessage(ApplicationCode.SUCCESS_MSG);
			baseResponseData.setData(response);
		} catch (ApplicationException e) {
			baseResponseData.setErrorCode(e.getCode());
			baseResponseData.setMessage(e.getMessage());
		} finally {

		}
		return new ResponseEntity<>(baseResponseData, HttpStatus.OK);
	}

	@PostMapping("api/account/addFacebook")
	public ResponseEntity<?> addFacebook(@RequestBody AddFacebookRequest request) {
		BaseResponseData baseResponseData = new BaseResponseData();
		try {
			if (!request.isValid()) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.REQUEST_ERROR_MSG);
			}
			accountService.addFacebookAction(request);
			baseResponseData.setErrorCode(ApplicationCode.SUCCESS);
			baseResponseData.setMessage(ApplicationCode.ADD_FB_MSG);
		} catch (ApplicationException e) {
			baseResponseData.setErrorCode(e.getCode());
			baseResponseData.setMessage(e.getMessage());
		} finally {

		}
		return new ResponseEntity<>(baseResponseData, HttpStatus.OK);
	}

	@PostMapping("api/account/addGoogle")
	public ResponseEntity<?> addGoogle(@RequestBody AddGoogleRequest request) {
		BaseResponseData baseResponseData = new BaseResponseData();
		try {
			if (!request.isValid()) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.REQUEST_ERROR_MSG);
			}
			accountService.addGoogleAction(request);
			baseResponseData.setErrorCode(ApplicationCode.SUCCESS);
			baseResponseData.setMessage(ApplicationCode.ADD_FB_MSG);
		} catch (ApplicationException e) {
			baseResponseData.setErrorCode(e.getCode());
			baseResponseData.setMessage(e.getMessage());
		} finally {

		}
		return new ResponseEntity<>(baseResponseData, HttpStatus.OK);
	}

	@PostMapping("api/account/deleteAccount")
	public ResponseEntity<?> addGoogle(@RequestBody DeleteAccountRequest request) {
		BaseResponseData baseResponseData = new BaseResponseData();
		try {
			if (!request.isValid()) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.REQUEST_ERROR_MSG);
			}
			accountService.deleteAccountAction(request);
			baseResponseData.setErrorCode(ApplicationCode.SUCCESS);
			baseResponseData.setMessage(ApplicationCode.DELETE_ACCOUNT);
		} catch (ApplicationException e) {
			baseResponseData.setErrorCode(e.getCode());
			baseResponseData.setMessage(e.getMessage());
		} finally {

		}
		return new ResponseEntity<>(baseResponseData, HttpStatus.OK);
	}

	@GetMapping("api/account/getListSocialAccount")
	public ResponseEntity<?> getListSocialAccount() {
		BaseResponseData baseResponseData = new BaseResponseData();
		try {
			GetListSocialAccountResponse response = accountService.getListSocialAccountAction();
			baseResponseData.setErrorCode(ApplicationCode.SUCCESS);
			baseResponseData.setMessage(ApplicationCode.SUCCESS_MSG);
			baseResponseData.setData(response);
		} catch (ApplicationException e) {
			baseResponseData.setErrorCode(e.getCode());
			baseResponseData.setMessage(e.getMessage());
		} finally {

		}
		return new ResponseEntity<>(baseResponseData, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest request) {
		BaseResponseData<CreateAccountRequest> responeData = new BaseResponseData<>();
		try {
			if (!request.isValid()) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.REQUEST_ERROR_MSG);
			}

			accountService.createAccount(request);
			responeData.setErrorCode(ApplicationCode.SUCCESS);
			responeData.setMessage(ApplicationCode.CREATE_ACCOUNT);
			responeData.setData(request);
		} catch (ApplicationException e) {
			responeData.setErrorCode(e.getCode());
			responeData.setMessage(e.getMessage());
		}
		return new ResponseEntity<>(responeData, HttpStatus.OK);
	}

	@PostMapping("api/account/update")
	public ResponseEntity<?> updateAccount(@RequestBody UpdateAccountRequest request) throws ApplicationException {
		BaseResponseData<AccountResponse> responeData = new BaseResponseData<>();
		try {
			if (!request.isValid()) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.REQUEST_ERROR_MSG);
			}
			AccountResponse account = accountService.updateAccount(request);
			responeData.setErrorCode(ApplicationCode.SUCCESS);
			responeData.setMessage(ApplicationCode.UPDATE_ACCOUNT);
			responeData.setData(account);
		} catch (ApplicationException e) {
			responeData.setErrorCode(e.getCode());
			responeData.setMessage(e.getMessage());
		}
		return new ResponseEntity<>(responeData, HttpStatus.OK);
	}

	@PostMapping("api/account/change-password")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) throws ApplicationException {
		BaseResponseData<AccountResponse> responeData = new BaseResponseData<>();
		try {
			if (!request.isValid()) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.REQUEST_ERROR_MSG);
			}
			AccountResponse account = accountService.changePassword(request);
			responeData.setErrorCode(ApplicationCode.SUCCESS);
			responeData.setMessage(ApplicationCode.CHANGE_PASSWORD_SUCCESS);
			responeData.setData(account);
		} catch (ApplicationException e) {
			responeData.setErrorCode(e.getCode());
			responeData.setMessage(e.getMessage());
		}
		return new ResponseEntity<>(responeData, HttpStatus.OK);
	}

	@GetMapping("api/account/info")
	public ResponseEntity<?> getUserInfo() {
		BaseResponseData<AccountResponse> responeData = new BaseResponseData<>();
		try {
			AccountResponse response = accountService.getUserInfo();
			responeData.setErrorCode(ApplicationCode.SUCCESS);
			responeData.setMessage(ApplicationCode.SUCCESS_MSG);
			responeData.setData(response);
		} catch (ApplicationException e) {
			responeData.setErrorCode(e.getCode());
			responeData.setMessage(e.getMessage());
		}
		return new ResponseEntity<>(responeData, HttpStatus.OK);
	}
}
