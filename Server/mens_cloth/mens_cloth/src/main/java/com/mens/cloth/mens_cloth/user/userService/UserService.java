package com.mens.cloth.mens_cloth.user.userService;

import com.mens.cloth.mens_cloth.auth.dto.ForgotPasswordDto;
import com.mens.cloth.mens_cloth.auth.dto.LoginDto;
import com.mens.cloth.mens_cloth.auth.dto.OtpDto;
import com.mens.cloth.mens_cloth.auth.dto.SignupDto;
import com.mens.cloth.mens_cloth.auth.entity.Token;
import com.mens.cloth.mens_cloth.auth.entity.VerificationCode;
import com.mens.cloth.mens_cloth.auth.enums.UserType;
import com.mens.cloth.mens_cloth.auth.repository.VerificationCodeRepository;
import com.mens.cloth.mens_cloth.auth.response.AuthResponse;
import com.mens.cloth.mens_cloth.auth.service.TokenService;
import com.mens.cloth.mens_cloth.auth.service.VerificationCodeService;
import com.mens.cloth.mens_cloth.common.error.DuplicateRecord;
import com.mens.cloth.mens_cloth.common.service.MailService;
import com.mens.cloth.mens_cloth.user.dto.UserDto;
import com.mens.cloth.mens_cloth.user.entity.User;
import com.mens.cloth.mens_cloth.user.enums.SignInType;
import com.mens.cloth.mens_cloth.user.enums.UserStatus;
import com.mens.cloth.mens_cloth.user.userRepository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DashboardUserService dashboardUserService;

    @Autowired
    private MailService mailService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;


    public VerificationCode userSignUp(SignupDto payload) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(payload.getEmail());
        if (userOptional.isPresent()) {
            throw new DuplicateRecord("user already exists");
        } else {

            User user = User.builder()
                    .name(payload.getName())
                    .email(payload.getEmail())
                    .mobile(payload.getMobile())
                    .password(passwordEncoder.encode(payload.getPassword()))
                    .status(UserStatus.ACTIVE)
                    .isVerified(false)
                    .role(payload.getRole())
                    .signInType(SignInType.EMAIL_PASSWORD)
                    .build();
            user = userRepository.save(user);
            if(payload.getRole() == UserType.CUSTOMER){
                customerService.createCustomer(user);
            }else {
                dashboardUserService.createDashboardUser(user);
            }
            VerificationCode verificationCode = verificationCodeService.createOtp(user.getId());
            mailService.sendEmail(payload.getEmail(), "Signup Verification From E-Cloth", verificationCode.getCode());

            return verificationCode;
        }

    }

    public void verifyOtp(OtpDto payload) {
        Optional<VerificationCode> verificationCodeOptional = verificationCodeRepository.findByIdAndCode(payload.getId(), payload.getOtp());

        if (verificationCodeOptional.isEmpty()) {
            throw new EntityNotFoundException("Invalid OTP");
        }
        VerificationCode verificationCode = verificationCodeOptional.get();

        verificationCode.setIsRevoked(true);
        verificationCode.getUser().setIsVerified(true);
        verificationCodeRepository.save(verificationCode);
    }

    public AuthResponse login(LoginDto payload) {
        Optional<User> userOptional = userRepository.findByEmail(payload.getEmail());

        if (userOptional.isEmpty()) {
            throw new BadCredentialsException("Invalid Email");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(payload.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }

        Token accessToken = tokenService.createAccessToken(user);
        Token refreshToken = tokenService.createRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(accessToken.getToken())
                .refreshToken(refreshToken.getToken())
                .user(user)
                .build();

    }

    public VerificationCode forgotPassword(String email) {
        System.out.print(email);
        Optional<User> userOptional = userRepository.findByEmail(email);
        System.out.print(userOptional);
        if (userOptional.isEmpty()) {
            throw new BadCredentialsException("Invalid Email");
        }
        VerificationCode verificationCode = verificationCodeService.createOtp(userOptional.get().getId());
        mailService.sendEmail(email, "Verification Code for Forgot Password", verificationCode.getCode());
        return verificationCode;

    }

    public void updatePassword(ForgotPasswordDto payload) {
        Optional<VerificationCode> verificationCodeOptional = verificationCodeRepository.findByCode(payload.getVerificationCode());

        if(verificationCodeOptional.isEmpty()){
            throw  new EntityNotFoundException("Invalid Otp");
        }
        User user = verificationCodeOptional.get().getUser();
        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        userRepository.save(user);
    }

    public List<User> fetchAllUser() {
        return  userRepository.findAll();
    }

    public User updateUser(Long userId, UserDto payload) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new EntityNotFoundException("User Not Found");
        }
        User user = userOptional.get();
        user.setName(payload.getName());
        user.setMobile(payload.getMobile());
       return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new EntityNotFoundException("User Not Found");
        }
        userRepository.deleteById(userId);
    }

    public User getUser(String currentUser) {
        Optional<User> userOptional = userRepository.findByEmail(currentUser);
        if(userOptional.isEmpty()){
            throw new EntityNotFoundException("User Not Found");
        }
        return userOptional.get();
    }
}
