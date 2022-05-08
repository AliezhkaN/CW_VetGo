<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>VetGo</title>
    <style>
        <%@include file="/styles/common/default.css" %>
        <%@include file="/styles/home/style.css" %>
        <%@include file="/styles/common/modals.css" %>
        <%@include file="/styles/common/header.css" %>
        <%@include file="/styles/common/button.css" %>
        <%@include file="/styles/common/visibility.css" %>
        <%@include file="/styles/common/input.css" %>
        <%@include file="/styles/common/footer.css" %>
    </style>
    <link rel="stylesheet"
          href="https://unpkg.com/swiper@8/swiper-bundle.min.css"/>
    <link rel="icon" type="image/x-icon" href="./images/logo.png">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://unpkg.com/jquery-input-mask-phone-number@1.0.15/dist/jquery-input-mask-phone-number.js"></script>
</head>
<body>
<div class="wrapper">
    <div class="stickyArrow hide">
        <img src="images/upArrow.png" alt="">
    </div>
    <header class="header">
        <div class="header__container">
            <a href="" class="header__logo">
                <img src="images/logo.svg" alt="logo">
            </a>
            <nav class="header__menu menu">
                <ul class="menu__list">
                    <li class="menu__item"><a href="" class="menu__link">HOME</a></li>
                    <li id="about" class="menu__item"><a href="" class="menu__link">ABOUT</a></li>
                    <li id="services" class="menu__item"><a href="" class="menu__link">SERVICES</a></li>
                    <li id="contactUs"class="menu__item"><a href="" class="menu__link">CONTACT US</a></li>
                    <c:if test="${user!=null}">
                        <li id="contactUs"class="menu__item"><a href="profile" class="menu__link">PROFILE</a></li>
                    </c:if>
                </ul>
            </nav>
            <div class="header__button">
                <c:choose>
                    <c:when test="${user!=null}">
                        <a  href="logout" class="button btn">LOG OUT</a>
                    </c:when>
                    <c:otherwise>
                        <a data-login-modal class="button btn">LOGIN</a>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
    </header>
    <main class="main">
        <section class="get-started">
            <div class="get-started__container">
                <div class="get-started__image">
                    <img src="images/pet.jpg" alt="pets">
                </div>
                <div class="get-started__content">
                    <div class="block-text">

                        <div class="block-text__text-container">
                            <div class="block-text__text">
                                <h1>Take care of yours pet’s <span>health.</span></h1>
                            </div>
                        </div>
                        <div class="block-text__button">
                            <c:choose>
                                <c:when test="${user!=null}">
                                    <a href="profile" class="button">Get Started</a>
                                </c:when>
                                <c:otherwise>
                                    <button data-login-modal href="" class="button">Get Started</button>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="about">
            <div class="about__container">
                <div class="about_content-container">
                    <div class="tabcontent doctors">
                        <div class="doctor-container">
                            <div class="doctor-image">
                                <img src="images/doctor.jpg" alt="jane">
                            </div>
                            <div class="doctor-name">
                                <p>Jane Dou</p>
                            </div>
                        </div>
                        <div class="doctor-container">
                            <div class="doctor-image">
                                <img src="images/doctor1.jpg" alt="jane">
                            </div>
                            <div class="doctor-name">
                                <p>John Dou</p>
                            </div>
                        </div>
                    </div>
                    <div class="tabcontent hide testimonials">
                        <div class="testimonials__contect">
                            <div class="testimonials__items">
                                <div class="testimonials__item">
                                    <div class="testimonial__title">
                                        <div class="name">Oleh Nahorniak</div>
                                        <div class="tag">@nahorniak</div>
                                    </div>
                                    <div class="testimonial__text-container">
                                        <div class="testimonial__text">
                                            “This is the best place I know of for veterinary care. I wouldn’t take my cat anywhere else!”
                                        </div>
                                    </div>
                                </div>
                                <div class="testimonials__item">
                                    <div class="testimonial__title">
                                        <div class="name">Carl Gallagher</div>
                                        <div class="tag">@c.gallagher</div>
                                    </div>
                                    <div class="testimonial__text-container">
                                        <div class="testimonial__text">
                                            “Everything was perfect, as usual. We love you guys!”
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="testimonials__contect ">
                            <div class="testimonials__items testimonials_second">
                                <div class="testimonials__item">
                                    <div class="testimonial__title">
                                        <div class="name">John Smith</div>
                                        <div class="tag">@j.smith</div>
                                    </div>
                                    <div class="testimonial__text-container">
                                        <div class="testimonial__text">
                                            “The care given to my animals has been nothing short of perfect! Thank you for doing a great job!”
                                        </div>
                                    </div>
                                </div>
                                <div class="testimonials__item ">
                                    <div class="testimonial__title">
                                        <div class="name">Andrew Dou</div>
                                        <div class="tag">@a.dou</div>
                                    </div>
                                    <div class="testimonial__text-container">
                                        <div class="testimonial__text">
                                            “Took a genuine interest in my dogs well being.”
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tabcontent hide what-to-expect">
                        <div class="wte-image-container">
                            <div class="i-c">
                                <img src="images/dog-cat.jpg" alt="">
                            </div>
                        </div>
                        <div class="wte-text-container">
                            <div class="wte-text-items">
                                <div class="wte-text">
                                    When you arrive for your appointment, you will be greeted warmly,
                                    and we’ll usher you into an examination room after checking in your pet.
                                </div>
                                <div class="wte-text">
                                    In most cases, one of our veterinary technicians will start by asking about your pet’s
                                    medical history, current condition and the reason for your visit, gathering data for the doctor.
                                </div>
                                <div class="wte-text">
                                    Then, one of our veterinarians will examine your pet, ask you further questions
                                    and then may recommend diagnostic tests if needed. Once the tests have been run,
                                    which can usually be done in the practice, your doctor will discuss a possible diagnosis and treatment plan if warranted.
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="about-text-container">
                        <div class="about__title-container">
                            <h1 class="about__title">ABOUT</h1>
                        </div>
                        <div class="tab__items_container">
                            <div class="tab__item tab__item_active">Our Doctors</div>
                            <div class="tab__item">Testimonials</div>
                            <div class="tab__item">What to expect</div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="services">
            <div class="services__container">
                <div class="s-container">
                    <div class="__title">
                        <p>Our Services</p>
                    </div>
                    <div class="swiper">

                        <!-- Additional required wrapper -->
                        <div class="swiper-wrapper">
                            <!-- Slides -->


                            <div class="swiper-slide">
                                <div class="service-image">
                                    <img src="images/services02.jpg" alt="">
                                </div>
                                <div class="service_text">
                                    <p>Animal Dental Care</p>
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="service-image">
                                    <img src="images/services01.jpg" alt="">
                                </div>
                                <div class="service_text">
                                    <p>Microchipping</p>
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="service-image">
                                    <img src="images/services03.jpg" alt="">
                                </div>
                                <div class="service_text">
                                    <p>Surgeries</p>
                                </div>
                            </div>

                            <div class="swiper-slide">
                                <div class="service-image">
                                    <img src="images/services04.jpg" alt="">
                                </div>
                                <div class="service_text">
                                    <p>Senior Pet Care</p>
                                </div>
                            </div>
                        </div>
                        <!-- If we need pagination -->
                        <div class="swiper-pagination"></div>
                        <!-- If we need navigation buttons -->
                        <!-- <div class="swiper-button-prev"></div>
                        <div class="swiper-button-next"></div> -->
                    </div>

                </div>
            </div>
        </section>
        <section class="contact_us">
            <div class="contact-us__container">
                <div class="c-container">
                    <div class="__title">
                        <p>Contact Us</p>
                    </div>
                    <div class="info-container">
                        <div class="info">
                            <div class="info-title">
                                <span>Our Center</span>
                            </div>
                            <div class="info-text">
                                <ul class="info-list">
                                    <li class="info-list-item">
                                        <div class="info-icon">
                                            <img src="images/phonelink-ring--v1.png" alt="">
                                        </div>
                                        <div class="label">
                                            Phone
                                        </div>
                                        <div class="info-content">
                                            +380 88 888 8888
                                        </div>
                                    </li>
                                    <li class="info-list-item">
                                        <div class="info-icon">
                                            <img src="images/new-post.png" alt="">
                                        </div>
                                        <div class="label">
                                            Mail
                                        </div>
                                        <div class="info-content">
                                            vet.go.ua@gmail.com
                                        </div>
                                    </li>
                                    <li class="info-list-item">
                                        <div class="info-icon">
                                            <img src="images/marker.png" alt="">
                                        </div>
                                        <div class="label">
                                            Location
                                        </div>
                                        <div class="info-content">
                                            Stepana Bandery Street,30
                                        </div>
                                    </li>
                                    <li class="info-list-item">
                                        <div class="info-icon">
                                            <img src="images/map.png" alt="">
                                        </div>
                                        <div class="label">
                                            Maps
                                        </div>
                                        <div class="info-content">
                                            <a target="_blank" href="https://www.google.com.ua/maps/place/5-%D0%B9+%D0%BA%D0%BE%D1%80%D0%BF%D1%83%D1%81+%D0%9D%D0%A3+%E2%80%9C%D0%9B%D1%8C%D0%B2%D1%96%D0%B2%D1%81%D1%8C%D0%BA%D0%B0+%D0%BF%D0%BE%D0%BB%D1%96%D1%82%D0%B5%D1%85%D0%BD%D1%96%D0%BA%D0%B0%E2%80%9D/@49.8340581,24.0094328,17.26z/data=!4m13!1m7!3m6!1s0x473add799fb83c41:0xe4f50a6accc5c42a!2z0LLRg9C70LjRhtGPINCh0YLQtdC_0LDQvdCwINCR0LDQvdC00LXRgNC4LCDQm9GM0LLRltCyLCDQm9GM0LLRltCy0YHRjNC60LAg0L7QsdC70LDRgdGC0YwsIDc5MDAw!3b1!8m2!3d49.8346615!4d24.0112797!3m4!1s0x0:0x699fc00c7adebdd8!8m2!3d49.8349796!4d24.0080744?hl=uk">
                                                Viev on Google Maps</a>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <form action="contact" method="post" class="contact-form">
                            <div class="form-title">
                                <div class="f_title">
                                    <span>Contact Form</span>
                                </div>
                                <div class="f-txt">
                                    <p>You have questions and we have answers.</p>
                                    <p>Contact us today, we’re here to help.</p>
                                </div>
                            </div>
                            <div class="input-container">
                                <div class="input-wrapper">
                                    <div id="c-name" class="hide">
                                        <label for="c-name-input">Full Name</label>
                                    </div>
                                    <input id="c-name-input" class="input" type="text" placeholder="Full Name" name="fullName">
                                    <div id="c-email" class="hide">
                                        <label  for="c-email-input">Email</label>
                                    </div>
                                    <input id="c-email-input" class="input" type="text" placeholder="Email" name="email">
                                    <div id="c-message" class="hide">
                                        <label  for="c-message-input">Message</label>
                                    </div>
                                    <div>
                                        <textarea class="" id="c-message-input" placeholder="Message" name="message"></textarea>
                                    </div>

                                    <div class="submit__button">
                                        <input type="submit" class="button btn" value="Submit">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <footer id="footer">
        <div id="empty"></div>
        <div class="footer-container">
            <div class="social-links">
                <a href="https://www.instagram.com" class="instagram">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
                         class="bi bi-instagram" viewBox="0 0 16 16">
                        <path d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z"/>
                    </svg>
                </a>
                <a href="https://web.telegram.org" class="telegram">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
                         class="bi bi-telegram" viewBox="0 0 16 16">
                        <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.287 5.906c-.778.324-2.334.994-4.666 2.01-.378.15-.577.298-.595.442-.03.243.275.339.69.47l.175.055c.408.133.958.288 1.243.294.26.006.549-.1.868-.32 2.179-1.471 3.304-2.214 3.374-2.23.05-.012.12-.026.166.016.047.041.042.12.037.141-.03.129-1.227 1.241-1.846 1.817-.193.18-.33.307-.358.336a8.154 8.154 0 0 1-.188.186c-.38.366-.664.64.015 1.088.327.216.589.393.85.571.284.194.568.387.936.629.093.06.183.125.27.187.331.236.63.448.997.414.214-.02.435-.22.547-.82.265-1.417.786-4.486.906-5.751a1.426 1.426 0 0 0-.013-.315.337.337 0 0 0-.114-.217.526.526 0 0 0-.31-.093c-.3.005-.763.166-2.984 1.09z"/>
                    </svg>
                </a>
                <a href="https://www.youtube.com/" class="youtube">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" class="bi bi-youtube"
                         viewBox="0 0 16 16">
                        <path d="M8.051 1.999h.089c.822.003 4.987.033 6.11.335a2.01 2.01 0 0 1 1.415 1.42c.101.38.172.883.22 1.402l.01.104.022.26.008.104c.065.914.073 1.77.074 1.957v.075c-.001.194-.01 1.108-.082 2.06l-.008.105-.009.104c-.05.572-.124 1.14-.235 1.558a2.007 2.007 0 0 1-1.415 1.42c-1.16.312-5.569.334-6.18.335h-.142c-.309 0-1.587-.006-2.927-.052l-.17-.006-.087-.004-.171-.007-.171-.007c-1.11-.049-2.167-.128-2.654-.26a2.007 2.007 0 0 1-1.415-1.419c-.111-.417-.185-.986-.235-1.558L.09 9.82l-.008-.104A31.4 31.4 0 0 1 0 7.68v-.123c.002-.215.01-.958.064-1.778l.007-.103.003-.052.008-.104.022-.26.01-.104c.048-.519.119-1.023.22-1.402a2.007 2.007 0 0 1 1.415-1.42c.487-.13 1.544-.21 2.654-.26l.17-.007.172-.006.086-.003.171-.007A99.788 99.788 0 0 1 7.858 2h.193zM6.4 5.209v4.818l4.157-2.408L6.4 5.209z"/>
                    </svg>
                </a>
            </div>
            <div class="copyright">
                &copy; Copyright <strong><span>VetGo</span></strong>. All Rights Reserved
            </div>

        </div>
    </footer>

    <div class="modal" id="login-modal">
        <div class="modal__dialog">
            <div class="modal__content">
                <form action="login" method="post">
                    <div data-loginClose class="modal__close">&times;</div>
                    <div class="modal__title">Login to your Vet<span>Go</span> account!</div>
                    <div class="flex-ja-center">
                        <div class="login__wrapper">
                            <div class="login__">
                                <div id="loginLabel" class="hide">
                                    <label  for="login">Email</label>
                                </div>
                                <div>
                                    <input autocomplete required placeholder="Email" id="login" name="email" type="text" class="input black">
                                </div>

                                <div id="passwordLabel" class="hide">
                                    <label for="password">Password</label>
                                </div>
                                <div>
                                    <input autocomplete id="password" required placeholder="Password" name="password" type="password" class="input black">
                                </div>
                            </div>
                            <input type="submit" class="button" value="Login">
                            <c:if test="${errorMessage != null}">
                                <div class="login__modal__message__wrapper">
                                    <span id="login-error"><c:out value="${errorMessage}"/></span>
                                </div>
                            </c:if>
                            <div class="login__modal__message__wrapper forgot__password__wrapper">
                                <a href="" id="pass-recovery">forgot password?</a>
                            </div>
                            <div class="login__modal__message__wrapper sign_up_wrapper">
                                <span>Don't have an account?</span><a id="sign__up" href="">Sign up</a>
                            </div>

                        </div>

                    </div>

                </form>
            </div>
        </div>
    </div>

    <div class="modal" id="sign__up__modal">
        <div class="modal__dialog" style="margin: 5% auto!important;">
            <div class="modal__content">
                <form action="signUp" method="post">
                    <div data-signUpClose class="modal__close">&times;</div>
                    <div class="modal__title">Create your Vet<span>Go</span> account!</div>
                    <div class="flex-ja-center">
                        <div class="login__wrapper">
                            <div class="login__">
                                <div id="s-email-label" class="hide">
                                    <label  for="s-email">Email</label>
                                </div>
                                <div>
                                    <input autocomplete required placeholder="Email" id="s-email" name="email" type="email" class="input black">
                                </div>
                                <div id="s-fName-label" class="hide">
                                    <label  for="s-fName">First Name</label>
                                </div>
                                <div>
                                    <input autocomplete required placeholder="First Name" id="s-fName" name="firstName" type="text" class="input black">
                                </div>
                                <div id="s-lName-label" class="hide">
                                    <label  for="s-lName">Last Name</label>
                                </div>
                                <div>
                                    <input autocomplete required placeholder="Last Name" id="s-lName" name="lastName" type="text" class="input black">
                                </div>
                                <div id="s-phone-label" class="hide">
                                    <label  for="s-phone">Phone Number</label>
                                </div>
                                <div>
                                    <input autocomplete required placeholder="Phone number" id="s-phone" maxlength="16" name="phoneNumber" type="tel" class="input black">
                                </div>
                                <div id="s-password-label" class="hide">
                                    <label  for="s-password">Password</label>
                                </div>
                                <div>
                                    <input autocomplete required placeholder="Password" id="s-password"  name="password" type="password" class="input black">
                                </div>
                            </div>
                            <input type="submit" class="button" value="Sign Up">
                            <c:if test="${signUpError != null}">
                                <div class="login__modal__message__wrapper">
                                    <span id="signUp-error"><c:out value="${signUpError}"/></span>
                                </div>
                            </c:if>
                            <div class="login__modal__message__wrapper forgot__password__wrapper">
                                <span>Have an account?</span><a id="login__" href="">Log in</a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal" id="recovery">
        <div class="modal__dialog">
            <div class="modal__content">
                <form action="forgotPassword" method="post">
                    <div data-recoveryClose class="modal__close">&times;</div>
                    <div class="modal__title">Trouble Logging In?</div>
                    <div class="recovery-tip">Enter your email, and we'll send you a code to recover your account password</div>
                    <div class="flex-ja-center">
                        <div class="login__wrapper">
                            <div class="login__">
                                <div id="r-email-label" class="hide">
                                    <label  for="r-email">Email</label>
                                </div>
                                <div>
                                    <input autocomplete required placeholder="Email" id="r-email" name="email" type="email" class="input black">
                                </div>
                            </div>
                            <input type="submit" class="button" value="Send Code">
                            <c:if test="${forgotPasswordError != null}">
                                <div class="login__modal__message__wrapper">
                                    <span id="recovery-error"><c:out value="${forgotPasswordError}"/></span>
                                </div>
                            </c:if>
                            <div class="login__modal__message__wrapper forgot__password__wrapper">
                                <span>Have an account?</span><a id="login_" href="">Log in</a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal" id="recovery-code">
        <div class="modal__dialog">
            <div class="modal__content">
                <form action="confirmCode" method="post">
                    <div data-recoveryCodeClose class="modal__close">&times;</div>
                    <div class="modal__title">Password Recovery</div>
                    <div class="recovery-tip">Please input recovery code from your email</div>
                    <div class="flex-ja-center">
                        <div class="login__wrapper">
                            <div class="login__">
                                <div id="r-code-label" class="hide">
                                    <label  for="r-code">Code</label>
                                </div>
                                <div>
                                    <input type="hidden" name="code" value="${code}">
                                    <input autocomplete required placeholder="Code" id="r-code" name="inputCode" type="text" maxlength="6" class="input black">
                                </div>
                            </div>
                            <input type="submit" class="button" value="Confirm Code">
                            <c:if test="${recoveryCodeError != null}">
                                <div class="login__modal__message__wrapper mh-7">
                                    <span id="recoveryCode-error"><c:out value="${recoveryCodeError}"/></span>
                                </div>
                            </c:if>

                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal" id="changePassword">
        <div class="modal__dialog">
            <div class="modal__content">
                <form action="changePasswordByEmail" method="post">
                    <div data-changePasswordClose class="modal__close">&times;</div>
                    <div class="modal__title">Password Recovery</div>
                    <div class="recovery-tip">Enter new Password and Confirm It</div>
                    <div class="flex-ja-center">
                        <div class="login__wrapper">
                            <div class="login__">
                                <div id="newPassword-label" class="hide">
                                    <label  for="newPassword">New password</label>
                                </div>
                                <div>
                                    <input autocomplete required placeholder="New password" id="newPassword" name="newPassword" type="password" class="input black">
                                </div>
                                <div id="confirmPassword-label" class="hide">
                                    <label  for="confirmPassword">Confirm new Password</label>
                                </div>
                                <div>
                                    <input autocomplete required placeholder="Confirm new password" id="confirmPassword" name="confirmPassword" type="password" class="input black">
                                </div>
                            </div>
                            <input type="submit" class="button" value="Change Password">
                            <c:if test="${changePasswordError != null}">
                                <div class="login__modal__message__wrapper mh-7">
                                    <span id="changePassword-error"><c:out value="${changePasswordError}"/></span>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="WEB-INF/jsp/message.jsp"></jsp:include>
</div>
<script type="text/javascript">
    <%@include file="js/home/index.js"%>
    <%@include file="js/home/loginModal.js"%>
    <%@include file="js/home/signUpModal.js"%>
    <%@include file="js/home/phoneNumber.js"%>
    <%@include file="js/home/recoveryModal.js"%>
    <%@include file="js/home/recoveryCodeModal.js"%>
    <%@include file="js/home/changePasswordModal.js"%>
</script>
<c:if test="${errorMessage != null}">
    <script>
        const modal = document.querySelector('#login-modal');
        modal.classList.add('show');
        modal.classList.remove('hide');
    </script>
    <% session.removeAttribute("errorMessage");%>
</c:if>
<c:if test="${signUpError != null}">
    <script>
        const modal = document.querySelector('#sign__up__modal');
        modal.classList.add('show');
        modal.classList.remove('hide');
    </script>
    <% session.removeAttribute("signUpError");%>
</c:if>
<c:if test="${forgotPasswordError != null}">
    <script>
        const modal = document.querySelector('#recovery');
        modal.classList.add('show');
        modal.classList.remove('hide');
    </script>
    <% session.removeAttribute("forgotPasswordError");%>
</c:if>
<c:if test="${code != null}">
    <script>
        const modal = document.querySelector('#recovery-code');
        modal.classList.add('show');
        modal.classList.remove('hide');
    </script>
    <% session.removeAttribute("code");%>
    <% session.removeAttribute("recoveryCodeError");%>
</c:if>
<c:if test="${changePassword != null}">
    <script>
        const modal = document.querySelector('#changePassword');
        modal.classList.add('show');
        modal.classList.remove('hide');
    </script>
    <% session.removeAttribute("changePassword");%>
    <% session.removeAttribute("changePasswordError");%>
</c:if>
<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
</body>
</html>