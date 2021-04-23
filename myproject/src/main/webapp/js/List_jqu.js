/**
 * 
 */
const toogleBtn = document.querySelector('.navbar__toogleBtn');
const icons = document.querySelector('.navbar__icons')
const search = document.querySelector('.search')

toogleBtn.addEventListener('click', ()=>{
    search.classList.toggle('active');
    icons.classList.toggle('active');
});