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

    let recoveryModal = document.querySelector('#recovery');
    const recoveryModalClose = document.querySelector('[data-recoveryClose]');
    recoveryModalClose.addEventListener('click', ()=> {
        hideRecoveryValues();
        closeModal(recoveryModal);
    });

    let loginModal = document.querySelector('#login-modal');
    document.querySelector('#login_').addEventListener('click',  async (event)=>{
        event.preventDefault();
        hideRecoveryValues();
        closeModal(recoveryModal);
        openModal(loginModal);
    });

    const emailLabel =document.querySelector('#r-email-label');
    const emailInput =document.querySelector('#r-email');

    inputFocus(emailInput,emailLabel);

    function hideRecoveryValues(){
        let recoveryError = document.querySelector('#recovery-error');
        if(recoveryError != null) recoveryError.innerHTML ='';
        emailInput.value = '';
        hideElem(emailLabel);
    }

});