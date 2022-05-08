
window.addEventListener('DOMContentLoaded', ()=>{

    function openModal(modal){
        modal.classList.add('show');
        modal.classList.remove('hide');
        document.body.style.overflow = 'hidden';
    }

    function hideElem(element){
        element.classList.add('hide');
        element.classList.remove('show','fade');
    }

    function inputFocus(input,label){
        input.addEventListener('focus',()=>{
            label.classList.add('show','fade');
            label.classList.remove('hide');
            input.classList.add('red');
            input.classList.remove('black');
        });


        input.addEventListener('blur',(event)=>{
            if(event.target.value == ''){
                hideElem(label);
                input.classList.add('black');
                input.classList.remove('red');
            }
        })
    }

    function closeModal(modal){
        modal.classList.add('hide');
        modal.classList.remove('show');
        document.body.style.overflow = '';
    }


    let signUpModal = document.querySelector('#sign__up__modal');
    const signUpModalClose = document.querySelector('[data-signUpClose]');

    signUpModalClose.addEventListener('click', ()=> {
        hideSignUpValues();
        closeModal(signUpModal);
    });

    const emailLabel =document.querySelector('#s-email-label');
    const emailInput =document.querySelector('#s-email');

    const fNameLabel =document.querySelector('#s-fName-label');
    const fNameInput =document.querySelector('#s-fName');

    const lNameLabel =document.querySelector('#s-lName-label');
    const lNameInput =document.querySelector('#s-lName');

    const phoneLabel =document.querySelector('#s-phone-label');
    const phoneInput =document.querySelector('#s-phone');

    const passwordLabel =document.querySelector('#s-password-label');
    const passwordInput =document.querySelector('#s-password');

    inputFocus(emailInput,emailLabel);
    inputFocus(fNameInput,fNameLabel);
    inputFocus(lNameInput,lNameLabel);
    inputFocus(phoneInput,phoneLabel);
    inputFocus(passwordInput,passwordLabel);

    function hideSignUpValues(){
        let signUpError = document.querySelector('#signUp-error');
        if(signUpError != null) signUpError.innerHTML ='';
        emailInput.value = '';
        fNameInput.value = '';
        lNameInput.value = '';
        phoneInput.value = '';
        passwordInput.value = '';
        hideElem(emailLabel);
        hideElem(fNameLabel);
        hideElem(lNameLabel);
        hideElem(phoneLabel);
        hideElem(passwordLabel);
    }

    let loginModal = document.querySelector('#login-modal');
    document.querySelector('#login__').addEventListener('click',  async (event)=>{
        event.preventDefault();
        hideSignUpValues();
        closeModal(signUpModal);
        openModal(loginModal);
    });


});

