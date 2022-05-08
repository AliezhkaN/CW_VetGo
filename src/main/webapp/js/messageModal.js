window.addEventListener('DOMContentLoaded', ()=>{
    function closeModal(modal){
        modal.classList.add('hide');
        modal.classList.remove('show');
        document.body.style.overflow = '';
    }

    const messageModal = document.querySelector('#message');
    const signUpModalClose = document.querySelector('[data-messageClose]');

    signUpModalClose.addEventListener('click', ()=> {
        closeModal(messageModal);
    });
});