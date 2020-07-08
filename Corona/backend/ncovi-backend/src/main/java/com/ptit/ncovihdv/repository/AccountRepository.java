package com.ptit.ncovihdv.repository;

import com.ptit.ncovihdv.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 04-Jun-2020
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    @Query("Select a "
            + "From Account a "
            + "Where a.accountUsername = :username "
            + "And a.accountStatus = :status")
    Account findAccountByUsernameAndStatus(@Param("username") String username,
                                           @Param("status") Integer status);

    @Query("Select a "
            + "From Account a "
            + "Where a.accountUsername = :username "
            + "And a.accountPassword = :password "
            + "And a.accountStatus = :status")
    Account findAccountByUsernameAndPasswordAndStatus(@Param("username") String username,
                                                      @Param("password") String password, @Param("status") Integer status);

    @Query("Select a "
            + "From Account a "
            + "Where a.userByUserId.userId = :userId "
            + "And a.accountType IN (1,2) "
            + "And a.accountStatus = :status")
    List<Account> getListAccountSocialByStatus(@Param("userId") Integer userId,
                                               @Param("status") Integer status);

    @Query("Select a "
            + "From Account a "
            + "Where a.accountUsername = :username  "
            + "And a.accountStatus = :status")
    Account findByAccountUsernameAndStatus(@Param("username") String username,
                                           @Param("status") Integer status);

    @Query("Select a "
            + "From Account a "
            + "Where a.accountUsername = :username  "
            + "And a.accountStatus = :status "
            + "And a.accountType = :type ")
    Account findByAccountUsernameAndStatusAndAccountType(@Param("username") String username,
                                                         @Param("status") Integer status, @Param("type") Integer type);
}
