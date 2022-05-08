window.addEventListener('DOMContentLoaded', ()=>{
    smoothScroll('.wrapper',500);


    //tabs

    const tabs = document.querySelectorAll('.tab__item'),
        tabsContent = document.querySelectorAll('.tabcontent'),
        tabsParent = document.querySelector('.tab__items_container');

    function hideTabContent(){
        tabsContent.forEach( item => {
            item.classList.add('hide');
            item.classList.remove('show','fade');
        });

        tabs.forEach( item => {
            item.classList.remove('tab__item_active');
        });
    }

    function showTabContent(i = 0){
        tabsContent[i].classList.add('show','fade');
        tabsContent[i].classList.remove('hide');
        tabs[i].classList.add('tab__item_active');
    }

    hideTabContent();
    showTabContent();

    tabsParent.addEventListener('click', (event) =>{
        const target = event.target;
        if(target && target.classList.contains('tab__item')){
            tabs.forEach((item,i)=>{
                if(target == item) {
                    hideTabContent();
                    showTabContent(i);
                }
            });
        }
    });

    //scroll
    function smoothScroll(target, duration){
        let targetElement = document.querySelector(target);
        let targetPosition = targetElement.getBoundingClientRect().top;
        let startPosition = window.pageYOffset;
        let distance = targetPosition - startPosition;
        let startTime = null;
        function animationScroll(currentTime){
            if(startTime === null)startTime = currentTime;
            let timeElapsed = currentTime - startTime;
            let run = ease(timeElapsed, startPosition, distance, duration);
            window.scrollTo(0,run);
            if(timeElapsed < duration) requestAnimationFrame(animationScroll);
        }

        function ease(t, b, c, d) {
            t /= d;
            t--;
            return c * Math.sqrt(1 - t*t) + b;
        }


        requestAnimationFrame(animationScroll);
    }


    const stickyArrow = document.querySelector('.stickyArrow')

    document.querySelector('#about').addEventListener('click', (event)=>{
        event.preventDefault();
        smoothScroll('.about',1000);
    });

    document.querySelector('#services').addEventListener('click', (event)=>{
        event.preventDefault();
        smoothScroll('.services',1000);
    });

    document.querySelector('#contactUs').addEventListener('click', (event)=>{
        event.preventDefault();
        smoothScroll('.contact_us',1000);
    });

    stickyArrow.addEventListener('click', (event)=>{
        event.preventDefault();
        smoothScroll('.wrapper',500);
    });

    window.addEventListener('scroll',(event) => {
        if(window.pageYOffset < 600){
            stickyArrow.classList.add('hide');
            stickyArrow.classList.remove('show');

        }
        else {
            stickyArrow.classList.add('fade');
            stickyArrow.classList.add('show');
            stickyArrow.classList.remove('hide');
        }
    });

    //Swiper
    const swiper = new Swiper('.swiper', {
        // Optional parameters
        direction: 'horizontal',
        loop: true,
        slidesPerView: 3,
        initialSlide: 0,
        spaceBetween: 60,


        // If we need pagination
        pagination: {
            el: '.swiper-pagination',
        },

        // Navigation arrows
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },

        // And if we need scrollbar
        scrollbar: {
            el: '.swiper-scrollbar',
        },
    });

});