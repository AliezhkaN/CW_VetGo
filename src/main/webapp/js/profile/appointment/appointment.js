window.addEventListener('DOMContentLoaded', ()=>{
    const modalTrigger = document.querySelectorAll('[data-modal]'),
        appointmentModal = document.querySelector('#newAppointment'),
        modalClose = document.querySelector('[data-close]');

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
            openModal(appointmentModal);
        }));

    modalClose.addEventListener('click', ()=> {
        hideValues();
        closeModal(appointmentModal);
    });

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

    const kindLabel =document.querySelector('#kindLabel');
    const kindInput = document.querySelector('#kind');

    const nameLabel =document.querySelector('#nameLabel');
    const nameInput = document.querySelector('#name');

    const datetimeLabel =document.querySelector('#datetimeLabel');
    const datetimeInput = document.querySelector('#datetime');

    let today = new Date().toISOString().slice(0, 16);
    datetimeInput.min = today;

    inputFocus(kindInput,kindLabel);
    inputFocus(nameInput,nameLabel);
    inputFocus(datetimeInput,datetimeLabel);

    function hideValues(){
        let loginError = document.querySelector('#appointmentError');
        if(loginError != null) loginError.innerHTML ='';
        kindInput.value = '';
        nameInput.value = '';
        datetimeInput.value = '';
        hideElem(kindLabel);
        hideElem(nameLabel);
        hideElem(datetimeLabel);
    }

    const timeInput = document.querySelector('#time');
    if(timeInput != null){
        const time = timeInput.value;

        function getTimeRemaining(endtime){
            let days,hours,minutes,seconds;
            const t = Date.parse(endtime) - Date.parse(new Date());

            if(t <= 0){
                days = 0;
                hours = 0;
                minutes = 0;
            }else{
                const hour = 1000 * 60 * 60;
                days = Math.floor(t/(hour * 24 ));
                hours = Math.floor((t/hour) % 24);
                minutes = Math.floor((t/1000/60) % 60);
            }


            return {
                'total' : t,
                'days' : days,
                'hours': hours,
                'minutes' : minutes,
            };
        }

        function formatTime(num){
            if(num >= 0 && num < 10){
                return "0"+num;
            }else{
                return num;
            }
        }

        function setClock(selector, endtime){
            const timer = document.querySelector(selector),
                days = timer.querySelector('#days'),
                hours = timer.querySelector('#hours'),
                minutes = timer.querySelector('#minutes'),
                timeInterval = setInterval(updateClock, 1000);

            updateClock();

            function updateClock(){
                const t = getTimeRemaining(endtime);
                days.innerHTML = formatTime(t.days);
                hours.innerHTML = formatTime(t.hours);
                minutes.innerHTML = formatTime(t.minutes);

                if(t.total <= 0) {
                    clearInterval(timeInterval);
                }
            }
        }

        setClock('.timer',time);
    }

});