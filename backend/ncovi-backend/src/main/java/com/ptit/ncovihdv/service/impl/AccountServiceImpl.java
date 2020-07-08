package com.ptit.ncovihdv.service.impl;

import com.ptit.ncovihdv.config.PropertiesConfig;
import com.ptit.ncovihdv.dto.request.*;
import com.ptit.ncovihdv.dto.response.*;
import com.ptit.ncovihdv.exception.ApplicationException;
import com.ptit.ncovihdv.filter.JwtService;
import com.ptit.ncovihdv.model.Account;
import com.ptit.ncovihdv.model.Role;
import com.ptit.ncovihdv.model.User;
import com.ptit.ncovihdv.repository.AccountRepository;
import com.ptit.ncovihdv.repository.UserRepository;
import com.ptit.ncovihdv.service.AccountService;
import com.ptit.ncovihdv.util.common.Constant;
import com.ptit.ncovihdv.util.common.DateUtil;
import com.ptit.ncovihdv.util.common.type.AccountType;
import com.ptit.ncovihdv.util.common.type.StatusEnum;
import com.ptit.ncovihdv.util.message.ApplicationCode;
import com.ptit.ncovihdv.util.thirdparty.UserInfo;
import com.ptit.ncovihdv.util.thirdparty.facebook.FacebookAuthentication;
import com.ptit.ncovihdv.util.thirdparty.google.GoogleAuthentication;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtService jwtService;

	@Autowired
	PropertiesConfig propertiesConfig;

	@Autowired
	private RoleServiceImpl roleService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public LoginResponse loginFacebookAction(LoginFacebookRequest request) throws ApplicationException {
		try {
			LoginResponse response = new LoginResponse();
			UserInfo userInfo = FacebookAuthentication.getInstance().getUser(request.getToken(),
					propertiesConfig.getFacebookAppSecret());
			if (ObjectUtils.isEmpty(userInfo)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.LOGIN_SOCIAL_FAIL_MSG);
			}
			Account account = accountRepository.findAccountByUsernameAndStatus(userInfo.getId(),
					StatusEnum.ACTIVE.getValue());
			if (ObjectUtils.isEmpty(account)) {
				//if account not exist, create new user and account
				Role role = roleService.getRoleByCode("ROLE_USER");
				User user = new User();
				user.setUserFullname(userInfo.getName());
				user.setRoleByRole(role);
				user.setUserCreatedAt(LocalDateTime.now());
				userRepository.save(user);

				Account accountCreate = new Account();
				accountCreate.setAccountUsername(userInfo.getId());
				accountCreate.setAccountName(userInfo.getName());
				accountCreate.setAccountPassword(" ");
				accountCreate.setAccountStatus(StatusEnum.ACTIVE.getValue());
				accountCreate.setAccountType(AccountType.FACEBOOK.getValue());
				accountCreate.setAccountCreatedAt(LocalDateTime.now());
				accountCreate.setUserByUserId(user);
				accountRepository.save(accountCreate);

				response.setAccountId(accountCreate.getAccountId());
				response.setAccountType(accountCreate.getAccountType());
				response.setAccountName(accountCreate.getAccountName());
				response.setToken(jwtService.generateTokenLogin(accountCreate.getAccountUsername()));
				response.setGender(user.getUserGender());
				response.setUserId(user.getUserId());
				response.setFullName(user.getUserFullname());
			}else {
				User user = account.getUserByUserId();
				if (ObjectUtils.isEmpty(user)) {
					throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.USER_NOT_FOUND_MSG);
				}
				// save new name if user change name of the facebook account
				if (!account.getAccountName().equals(userInfo.getName())) {
					account.setAccountName(userInfo.getName());
					accountRepository.save(account);
				}
				response.setAccountId(account.getAccountId());
				response.setAccountType(account.getAccountType());
				response.setAccountName(account.getAccountName());
				response.setToken(jwtService.generateTokenLogin(account.getAccountUsername()));
				response.setGender(user.getUserGender());
				response.setUserId(user.getUserId());
				response.setFullName(user.getUserFullname());
			}

			return response;
		} catch (ApplicationException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException(ApplicationCode.ERROR, e.toString());
		}
	}

	@Override
	public LoginResponse loginGoogleAction(LoginGoogleRequest request) throws ApplicationException {
		try {
			LoginResponse response = new LoginResponse();
			UserInfo userInfo = GoogleAuthentication.getInstance().getUser(request.getToken());
			if (ObjectUtils.isEmpty(userInfo)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.LOGIN_SOCIAL_FAIL_MSG);
			}
			Account account = accountRepository.findAccountByUsernameAndStatus(userInfo.getId(),
					StatusEnum.ACTIVE.getValue());

			if (ObjectUtils.isEmpty(account)) {
				//if account not exist, create new user and account
				Role role = roleService.getRoleByCode("ROLE_USER");
				User user = new User();
				user.setUserFullname(userInfo.getName());
				user.setRoleByRole(role);
				user.setUserCreatedAt(LocalDateTime.now());
				user.setUserEmail(userInfo.getEmail());
				userRepository.save(user);

				Account accountCreate = new Account();
				accountCreate.setAccountUsername(userInfo.getId());
				accountCreate.setAccountName(userInfo.getName());
				accountCreate.setAccountPassword(" ");
				accountCreate.setAccountStatus(StatusEnum.ACTIVE.getValue());
				accountCreate.setAccountType(AccountType.GOOGLE.getValue());
				accountCreate.setAccountCreatedAt(LocalDateTime.now());
				accountCreate.setUserByUserId(user);
				accountRepository.save(accountCreate);

				response.setAccountId(accountCreate.getAccountId());
				response.setAccountType(accountCreate.getAccountType());
				response.setAccountName(accountCreate.getAccountName());
				response.setToken(jwtService.generateTokenLogin(accountCreate.getAccountUsername()));
				response.setGender(user.getUserGender());
				response.setUserId(user.getUserId());
				response.setFullName(user.getUserFullname());
			}else{
				User user = account.getUserByUserId();
				if (ObjectUtils.isEmpty(user)) {
					throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.USER_NOT_FOUND_MSG);
				}
				response.setAccountId(account.getAccountId());
				response.setAccountType(account.getAccountType());
				response.setAccountName(account.getAccountName());
				response.setToken(jwtService.generateTokenLogin(account.getAccountUsername()));
				response.setGender(user.getUserGender());
				response.setUserId(user.getUserId());
				response.setFullName(user.getUserFullname());
			}

			return response;
		} catch (ApplicationException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException(ApplicationCode.ERROR, e.toString());
		}
	}

	@Override
	public LoginResponse loginByUsernameAction(LoginByUsernameRequest request) throws ApplicationException {
		try {
			LoginResponse response = new LoginResponse();
			Account account = accountRepository.findAccountByUsernameAndStatus(request.getUsername(),
					StatusEnum.ACTIVE.getValue());
			if (ObjectUtils.isEmpty(account)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.ACCOUNT_NOT_EXIST_MSG);
			}

			boolean isAccount = passwordEncoder().matches(request.getPassword(), account.getAccountPassword());

			if (!isAccount) {
				return null;
			}

			User user = account.getUserByUserId();
			if (ObjectUtils.isEmpty(user)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.USER_NOT_FOUND_MSG);
			}
			response.setAccountId(account.getAccountId());
			response.setAccountType(account.getAccountType());
			response.setAccountName(account.getAccountName());
			response.setToken(jwtService.generateTokenLogin(account.getAccountUsername()));
			response.setGender(user.getUserGender());
			response.setUserId(user.getUserId());
			response.setFullName(user.getUserFullname());

			return response;
		} catch (ApplicationException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException(ApplicationCode.ERROR, e.toString());
		}
	}

	@Override
	public void addFacebookAction(AddFacebookRequest request) throws ApplicationException {
		try {
			String username = Constant.getCurrentUser();
			Account accountToken = accountRepository.findAccountByUsernameAndStatus(username, StatusEnum.ACTIVE.getValue());
			User user = accountToken.getUserByUserId();
			if (ObjectUtils.isEmpty(user)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.USER_NOT_FOUND_MSG);
			}
			UserInfo userInfo = FacebookAuthentication.getInstance().getUser(request.getToken(),
					propertiesConfig.getFacebookAppSecret());
			if (ObjectUtils.isEmpty(userInfo)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.LOGIN_SOCIAL_FAIL_MSG);
			}
			Account accountCheck = accountRepository.findAccountByUsernameAndStatus(userInfo.getId(),
					StatusEnum.ACTIVE.getValue());
			if (!ObjectUtils.isEmpty(accountCheck)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.ACCOUNT_HAS_EXIST_MSG);
			}
			Account accountInsert = new Account();
			accountInsert.setAccountUsername(userInfo.getId());
			accountInsert.setAccountName(userInfo.getName());
			accountInsert.setAccountStatus(StatusEnum.ACTIVE.getValue());
			accountInsert.setAccountType(AccountType.FACEBOOK.getValue());
			accountInsert.setAccountCreatedAt(LocalDateTime.now());
			accountInsert.setUserByUserId(user);

			accountRepository.save(accountInsert);
		} catch (ApplicationException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException(ApplicationCode.ERROR, e.toString());
		}
	}

	@Override
	public void addGoogleAction(AddGoogleRequest request) throws ApplicationException {
		try {
			String username = Constant.getCurrentUser();
			Account accountToken = accountRepository.findAccountByUsernameAndStatus(username, StatusEnum.ACTIVE.getValue());
			User user = accountToken.getUserByUserId();
			if (ObjectUtils.isEmpty(user)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.USER_NOT_FOUND_MSG);
			}
			UserInfo userInfo = GoogleAuthentication.getInstance().getUser(request.getToken());
			if (ObjectUtils.isEmpty(userInfo)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.LOGIN_SOCIAL_FAIL_MSG);
			}
			Account accountCheck = accountRepository.findAccountByUsernameAndStatus(userInfo.getId(),
					StatusEnum.ACTIVE.getValue());
			if (!ObjectUtils.isEmpty(accountCheck)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.ACCOUNT_HAS_EXIST_MSG);
			}
			Account accountInsert = new Account();
			accountInsert.setAccountUsername(userInfo.getId());
			accountInsert.setAccountName(userInfo.getName());
			accountInsert.setAccountStatus(StatusEnum.ACTIVE.getValue());
			accountInsert.setAccountType(AccountType.GOOGLE.getValue());
			accountInsert.setAccountCreatedAt(LocalDateTime.now());
			accountInsert.setUserByUserId(user);

			accountRepository.save(accountInsert);
		} catch (ApplicationException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException(ApplicationCode.ERROR, e.toString());
		}
	}

	@Override
	public void deleteAccountAction(DeleteAccountRequest request) throws ApplicationException {
		try {
			String username = Constant.getCurrentUser();
			Account account = accountRepository.findAccountByUsernameAndStatus(username, StatusEnum.ACTIVE.getValue());
			if (ObjectUtils.isEmpty(account)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.ACCOUNT_NOT_EXIST_MSG);
			}
			if (account.getAccountId() == request.getAccountId()){
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.DELETE_ACCOUNT_USING_MSG);
			}
			Account accountDelete = accountRepository.findById(request.getAccountId()).orElse(null);
			if (ObjectUtils.isEmpty(accountDelete)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.ACCOUNT_NOT_EXIST_MSG);
			}
			accountDelete.setAccountStatus(StatusEnum.DELETE.getValue());
			accountRepository.save(accountDelete);
		} catch (ApplicationException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException(ApplicationCode.ERROR, e.toString());
		}
	}

	@Override
	public Account findAccountExist(String username) {
		return accountRepository.findAccountByUsernameAndStatus(username, StatusEnum.ACTIVE.getValue());
	}

	@Override
	public GetListSocialAccountResponse getListSocialAccountAction()
			throws ApplicationException {
		try {
			GetListSocialAccountResponse response = new GetListSocialAccountResponse();
			String username = Constant.getCurrentUser();
			Account accountToken = accountRepository.findAccountByUsernameAndStatus(username, StatusEnum.ACTIVE.getValue());
			if (ObjectUtils.isEmpty(accountToken)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.USER_NOT_FOUND_MSG);
			}
			User user = accountToken.getUserByUserId();
			if (ObjectUtils.isEmpty(user)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.USER_NOT_FOUND_MSG);
			}
			List<Account> listAccountSocial = accountRepository.getListAccountSocialByStatus(user.getUserId(),
					StatusEnum.ACTIVE.getValue());
			List<SocialAccountResponse> listFacebook = new ArrayList<>();
			List<SocialAccountResponse> listGoogle = new ArrayList<>();

			for (Account account : listAccountSocial) {
				if (account.getAccountType() == AccountType.FACEBOOK.getValue()) {
					SocialAccountResponse accountResponse = new SocialAccountResponse();
					accountResponse.setAccountId(account.getAccountId());
					accountResponse.setName(account.getAccountName());
					accountResponse.setUserId(user.getUserId());
					accountResponse.setUsername(account.getAccountUsername());
					accountResponse.setType(account.getAccountType());
					listFacebook.add(accountResponse);
				} else if (account.getAccountType() == AccountType.GOOGLE.getValue()) {
					SocialAccountResponse accountResponse = new SocialAccountResponse();
					accountResponse.setAccountId(account.getAccountId());
					accountResponse.setName(account.getAccountName());
					accountResponse.setUserId(user.getUserId());
					accountResponse.setUsername(account.getAccountUsername());
					accountResponse.setType(account.getAccountType());
					listFacebook.add(accountResponse);
				}
			}

			response.setListFacebook(listFacebook);
			response.setListGoogle(listGoogle);

			return response;
		} catch (ApplicationException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException(ApplicationCode.ERROR, e.toString());
		}

	}

	@Override
	public void createAccount(CreateAccountRequest createAccountRequest) {
		Role role = roleService.getRoleByCode("ROLE_USER");
		User user = new User();
		user.setUserFullname(createAccountRequest.getFullname());
		user.setUserEmail(createAccountRequest.getEmail());
		user.setUserCreatedAt(DateUtil.getCreateAt());
		user.setRoleByRole(role);
		userRepository.save(user);
		Account account = new Account();
		account.setAccountName(createAccountRequest.getFullname());
		account.setAccountUsername(createAccountRequest.getUsername());
		account.setAccountPassword(passwordEncoder().encode(createAccountRequest.getPassword()));
		account.setUserByUserId(user);
		account.setAccountStatus(StatusEnum.ACTIVE.getValue());
		account.setAccountType(AccountType.NORMAL.getValue());
		account.setAccountCreatedAt(DateUtil.getCreateAt());
		accountRepository.save(account);
	}

	@Override
	public AccountResponse updateAccount(UpdateAccountRequest request) throws ApplicationException {
		try {
			String username = Constant.getCurrentUser();
			Account account = accountRepository.findAccountByUsernameAndStatus(username, StatusEnum.ACTIVE.getValue());
			if (ObjectUtils.isEmpty(account)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.USER_NOT_FOUND_MSG);
			}
			User user = account.getUserByUserId();
			user.setUserFullname(request.getUserFullname());
			user.setUserAddress(request.getUserAddress());
			user.setUserBhxh(request.getUserBhxh());
			user.setUserBirthday(request.getUserBirthday());
			user.setUserEmail(request.getUserEmail());
			user.setUserGender(request.getUserGender());
			user.setUserPhoneNo(request.getUserPhoneNo());
			user.setUserCmt(request.getUserCmt());
			userRepository.save(user);
			account.setUserByUserId(user);
			accountRepository.save(account);
			AccountResponse accountResponse = new AccountResponse();
			BeanUtils.copyProperties(account.getUserByUserId(), accountResponse);
			accountResponse.setUsername(account.getAccountUsername());
			return accountResponse;
		} catch (ApplicationException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException(ApplicationCode.ERROR, e.toString());
		}
	}

	@Override
	public AccountResponse changePassword(ChangePasswordRequest request) throws ApplicationException {
		try {
			String username = Constant.getCurrentUser();
			Account account = accountRepository.findByAccountUsernameAndStatusAndAccountType(username,
					StatusEnum.ACTIVE.getValue(), AccountType.NORMAL.getValue());
			if (ObjectUtils.isEmpty(account)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.USER_NOT_FOUND_MSG);
			}
			boolean isEquals = request.getOldPassword().equals(request.getNewPassword());
			if (isEquals) {
				throw new ApplicationException(ApplicationCode.ERROR,
						ApplicationCode.OLD_PASSWORD_NOT_SAME_NEW_PASSWORD);
			}

			boolean isCheckOldPassword = passwordEncoder().matches(request.getOldPassword(),
					account.getAccountPassword());

			if (!isCheckOldPassword) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.OLD_PASSWORD_INCORRECT);
			} else {
				String newPassword = passwordEncoder().encode(request.getNewPassword());
				account.setAccountPassword(newPassword);
				accountRepository.save(account);
				AccountResponse accountResponse = new AccountResponse();
				BeanUtils.copyProperties(account.getUserByUserId(), accountResponse);
				accountResponse.setUsername(account.getAccountUsername());
				return accountResponse;
			}

		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException(ApplicationCode.ERROR, e.toString());
		}
	}

	@Override
	public AccountResponse getUserInfo() throws ApplicationException {
		try {
			String username = Constant.getCurrentUser();
			Account account = accountRepository.findAccountByUsernameAndStatus(username, StatusEnum.ACTIVE.getValue());
			if (ObjectUtils.isEmpty(account)) {
				throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.USER_NOT_FOUND_MSG);
			}
			AccountResponse accountResponse = new AccountResponse();
			BeanUtils.copyProperties(account.getUserByUserId(), accountResponse);
			accountResponse.setUsername(account.getAccountUsername());
			return accountResponse;
		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException(ApplicationCode.ERROR, e.toString());
		}
	}
}
