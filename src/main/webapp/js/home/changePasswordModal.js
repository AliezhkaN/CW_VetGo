window.addEventListener('DOMContentLoaded', ()=>{

    function closeModal(modal){
        modal.classList.add('hide');
        modal.classList.remove('show');
        document.body.style.overflow = '';
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

    let recoveryCodeModal = document.querySelector('#changePassword');
    const recoveryCodeModalClose = document.querySelector('[data-changePasswordClose]');
    recoveryCodeModalClose.addEventListener('click', ()=> {
        hideChangePasswordValues();
        closeModal(recoveryCodeModal);
    });

    const newPasswordLabel =document.querySelector('#newPassword-label');
    const newPasswordInput =document.querySelector('#newPassword');

    const confirmPasswordLabel =document.querySelector('#confirmPassword-label');
    const confirmPasswordInput =document.querySelector('#confirmPassword');

    inputFocus(newPasswordInput,newPasswordLabel);
    inputFocus(confirmPasswordInput,confirmPasswordLabel);


    function hideChangePasswordValues(){
        let recoveryError = document.querySelector('#recoveryCode-error');
        if(recoveryError != null) recoveryError.innerHTML ='';
        newPasswordInput.value = '';
        hideElem(newPasswordLabel);
        confirmPasswordInput.value = '';
        hideElem(confirmPasswordLabel);
    }
});