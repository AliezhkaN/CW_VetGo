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


    let recoveryCodeModal = document.querySelector('#recovery-code');
    const recoveryCodeModalClose = document.querySelector('[data-recoveryCodeClose]');
    recoveryCodeModalClose.addEventListener('click', ()=> {
        hideRecoveryCodeValues();
        closeModal(recoveryCodeModal);
    });

    const codeLabel =document.querySelector('#r-code-label');
    const codeInput =document.querySelector('#r-code');

    inputFocus(codeInput,codeLabel);

    function hideRecoveryCodeValues(){
        let recoveryError = document.querySelector('#recoveryCode-error');
        if(recoveryError != null) recoveryError.innerHTML ='';
        codeInput.value = '';
        hideElem(codeLabel);
    }

    const isNumericInput = (event) => {
        const key = event.keyCode;
        return ((key >= 48 && key <= 57) || // Allow number line
            (key >= 96 && key <= 105) // Allow number pad
        );
    };

    const isModifierKey = (event) => {
        const key = event.keyCode;
        return (event.shiftKey === true || key === 35 || key === 36) || // Allow Shift, Home, End
            (key === 8 || key === 9 || key === 13 || key === 46) || // Allow Backspace, Tab, Enter, Delete
            (key > 36 && key < 41) || // Allow left, up, right, down
            (
                // Allow Ctrl/Command + A,C,V,X,Z
                (event.ctrlKey === true || event.metaKey === true) &&
                (key === 65 || key === 67 || key === 86 || key === 88 || key === 90)
            )
    };

    const enforceFormat = (event) => {
        // Input must be of a valid number format or a modifier key, and not longer than ten digits
        if(!isNumericInput(event) && !isModifierKey(event)){
            event.preventDefault();
        }
    };

    codeInput.addEventListener('keydown',enforceFormat);

});