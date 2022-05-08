window.addEventListener('DOMContentLoaded', ()=>{
    const modalTrigger = document.querySelectorAll('[data-login-modal]'),
        loginModal = document.querySelector('#login-modal'),
        modalClose = document.querySelector('[data-loginClose]');



    function openModal(modal){
        modal.classList.add('show');
        modal.classList.remove('hide');
        document.body.style.overflow = 'hidden';
    }


    function closeModal(modal){
        modal.classList.add('hide');
        modal.classList.remove('show');
        document.body.style.overflow = '';
    }



    function hideElem(element){
        element.classList.add('hide');
        element.classList.remove('show','fade');
    }

    modalTrigger.forEach(item =>
        item.addEventListener('click', (event)=>{
            event.preventDefault();
            openModal(loginModal);
        }));

    function hideLoginValues(){
        let loginError = document.querySelector('#login-error');
        if(loginError != null) loginError.innerHTML ='';
        emailInput.value = '';
        passwordInput.value = '';
        hideElem(loginLabel);
        hideElem(passwordLabel);
    }

    modalClose.addEventListener('click', ()=> {
        hideLoginValues();
        closeModal(loginModal);
    });



    document.addEventListener('keydown', (e)=> {
        if(e.code == "Escape" && loginModal.classList.contains('show')){
            hideLoginValues();
            closeModal(loginModal);
        }
    });

    //inputs
    const loginLabel =document.querySelector('#loginLabel');
    const emailInput = document.querySelector('#login');

    const passwordLabel = document.querySelector('#passwordLabel');
    const passwordInput = document.querySelector('#password');

    const contactNameLabel = document.querySelector('#c-name');
    const contactNameInput = document.querySelector('#c-name-input');

    const contactEmailLabel = document.querySelector('#c-email');
    const contactEmailInput = document.querySelector('#c-email-input');

    const contactMessageLabel = document.querySelector('#c-message');
    const contactMessageInput = document.querySelector('#c-message-input');

    inputFocus(emailInput,loginLabel);
    inputFocus(passwordInput,passwordLabel);
    inputFocus(contactNameInput,contactNameLabel);
    inputFocus(contactEmailInput,contactEmailLabel);
    inputFocus(contactMessageInput,contactMessageLabel);

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

    let signUpModal = document.querySelector('#sign__up__modal');
    document.querySelector('#sign__up').addEventListener('click',  async (event)=>{
        event.preventDefault();
        hideLoginValues();
        closeModal(loginModal);
        openModal(signUpModal);
    });
    let recoveryModal = document.querySelector('#recovery');
    document.querySelector('#pass-recovery').addEventListener('click',async (event)=>{
        event.preventDefault();
        hideLoginValues();
        closeModal(loginModal);
        openModal(recoveryModal);
    });
});
