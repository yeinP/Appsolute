/*!
    * Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2023 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    // 
// Scripts
// 
//
// window.addEventListener('DOMContentLoaded', event => {
//
//     // Toggle the side navigation
//     const sidebarToggle = document.body.querySelector('#sidebarToggle');
//     if (sidebarToggle) {
//         // Uncomment Below to persist sidebar toggle between refreshes
//         // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
//         //     document.body.classList.toggle('sb-sidenav-toggled');
//         // }
//         sidebarToggle.addEventListener('click', event => {
//             event.preventDefault();
//             document.body.classList.toggle('sb-sidenav-toggled');
//             localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
//         });
//     }
//
// });

window.addEventListener('DOMContentLoaded', event => {
    const sidebarToggle = document.body.querySelector('#sidebarToggle');

    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            const body = document.body;

            // 토글 버튼 클릭 시 사이드바 토글
            body.classList.toggle('sb-sidenav-toggled');

            // 토글 바가 닫힐 때
            if (!body.classList.contains('sb-sidenav-toggled')) {
                const showAsideBtn = document.querySelector('.show-side-btn');

                // 토글 버튼을 왼쪽 끝으로 이동
                sidebarToggle.style.marginLeft = '0';
            } else {
                // 토글 바가 열릴 때
                sidebarToggle.style.marginLeft = '-225px'; // 사이드바 폭에 맞게 조절 (원하는 값으로 변경)
            }

            localStorage.setItem('sb|sidebar-toggle', body.classList.contains('sb-sidenav-toggled'));
        });
    }
});

const modal = document.getElementById("modal")
function modalOn() {
    modal.style.display = "flex"
}
function isModalOn() {
    return modal.style.display === "flex"
}
function modalOff() {
    modal.style.display = "none"
}
const btnModal = document.getElementById("btn-modal")
btnModal.addEventListener("click", e => {
    modalOn()
})
const closeBtn = modal.querySelector(".close-area")
closeBtn.addEventListener("click", e => {
    modalOff()
})
modal.addEventListener("click", e => {
    const evTarget = e.target
    if(evTarget.classList.contains("modal-overlay")) {
        modalOff()
    }
})
window.addEventListener("keyup", e => {
    if(isModalOn() && e.key === "Escape") {
        modalOff()
    }
})

const modifyBtn = document.getElementById('btn-success');
modifyBtn.addEventListener("click", function (){
    const userConfirmed = confirm("수정하시겠습니까?");
    if(userConfirmed){
        return true;
    } else {
        return false;
    }
});
//
// var calendar;
//
// document.addEventListener('DOMContentLoaded', function (){
//     var calendarEl = document.getElementById('calendar');
//     const calendarDate = () => {
//         const firstAjax = new Promise((resolve, reject)=> {
//             $.ajax({
//                 url:'/attend/vacationAllList',
//                 type: 'get',
//                 success: function (data) {
//                     console.log("휴가 date", data);
//                     var events = data.map(function (event){
//                         return {
//                             id: event.id,
//                             title: '[' + event.vacationType.name + ']' + event.user.name + '님',
//                             start : event.startDate,
//                             end: event.endDate,
//                             textColor: 'white',
//                             color: "rgba( 35, 184, 116)",
//                             extendedProps: {
//                                 user: event.user.name,
//                                 title: '[' + event.vacationType.name + ']' + event.user.name + '님',
//                                 content: event.reason
//                             }
//                         };
//                     });
//                     resolve(events);
//                 },
//                 error: function (e){
//                     console.log("error" + e);
//                     reject(e);
//                 }
//             });
//         });
//         const secondAjax = new Promise((resolve,reject) => {
//             $.ajax({
//                 url: '/attend/scheduleAllList',
//                 type: 'get',
//                 success: function (data) {
//                     console.log("일정 data", data);
//                     const events = data.map(function (event){
//                         return {
//                             id: event.id,
//                             title: '[일정]' + event.user.name + '님',
//                             start : event.startDate,
//                             end: event.endDate,
//                             textColor: 'white',
//                             color: "rgb(3,120,171)",
//                             extendedProps: {
//                                 user: event.user.name,
//                                 title: '[일정]' + event.user.name + '님',
//                                 subtitle: event.title,
//                                 content: event.contents
//                             }
//                         };
//                     });
//                     resolve(events);
//                 },
//                 error: function (e){
//                     reject(e);
//                 }
//             });
//         });
//         return Promise.all([firstAjax, secondAjax]).then((results) => {
//             const [events, additionalEvents] = results;
//             return events.concat(additionalEvents);
//         });
//     };
//     calendarDate().then((events)=> {
//         calendar = new FullCalendar.Calendar(calendarEl, {
//             plugins:['interaction', 'dayGrid'],
//             local: 'kr',
//             expandRows: true,
//             editable: false,
//             displayEventTime: false,
//             dayMaxEventRows: 5,
//             eventLimit: true,
//             select: function (info) {
//                 handleDateSelection(info);
//             },
//             eventClick: function(info) {
//                 if(info.event.extendedProps) {
//                     var title = info.event.extendedProps.title;
//                     var subtitle = info.event.extendedProps.subtitle;
//                     var contents = info.event.extendedProps.contents;
//                     var user = info.event.extendedProps.user;
//
//                     var popoverTitle = '<div style = "font-weight: bold;">' + title + '</div>';
//                     var popoverSubtitle = '<div>일정:' + subtitle + '</div>' + '';
//                     var popoverContent = '<div>상세 내용: ' + contents + '</div>' +
//                         '<div>날짜: ' + moment(info.event.start).format('YYYY-MM-DD') + '~' + moment(info.event.end).format('YYYY-MM-DD') + '</div>';
//                     var popover = new bootstrap.Popover(info.el, {
//                         title: popoverTitle,
//                         content: popoverSubtitle + popoverContent,
//                         placement: 'auto',
//                         trigger: 'manual',
//                         html: true
//                     });
//                     popover.show();
//
//                     setTimeout(function (){
//                         popover.hide();
//                     }, 3000);
//                 }
//             },
//         });
//         calendar.render();
//     }).catch((e)=> {
//         console.error("error",e);
//     });
// })
//
// const saveScheduleCheck = (scheduleInfo)=> {
//     if(!scheduleInfo.title){
//         alert("일정 제목을 적어주세요.");
//         this.disabled = false;
//         return false;
//     }
//     if(!scheduleInfo.contents) {
//         alert("일정 내용을 적어주세요.");
//         this.disabled = false;
//         return false;
//     }
//     return true;
// }
// function handleDateSelection(info) {
//     const startDate = moment(info.startStr).format('YYYY-MM-DD');
//     const endDate = moment(info.endStr).format('YYYY-MM-DD');
//
//     openModalForDateSelection({info, startStr: startDate, endStr: endDate});
// }
//
// function  openModalFroDateSelection({info,startStr,endStr}){
//     const modalOpen = document.getElementById('modalContainer');
//     const srcollLockElment = document.body;
//
//     if(modalOpen.style.display === 'none' || modalOpen.style.display === ""){
//         modalOpen.style.display = "contents";
//         modalOpen.style.visibility = "visivle";
//         srcollLockElment.classList.add('scroll-lock');
//
//         $("#saveSchedule").load("/attend/openSaveSchedule", () => {
//             const startDate= moment(strStr).format('YYYY-MM-DD');
//             const endDate= moment(endStr).subtract(1, 'day').format('YYYY-MM-DD');
//
//             $("input[name='dateFilter']").val(startDate + '~' + endDate);
//             const scheduleModalClose = $('#modalCloseButton');
//             const scheduleModalSend = $('#modalSendButton');
//
//             scheduleModalClose.on('click', () => {
//                 modalOpen.style.display = "none";
//                 modalOpen.style.visibility = "hidden";
//                 srcollLockElment.classList.remove('scroll-lock');
//                 $("input[name='dateFilter']").val("");
//                 $("input[name='title']").val("");
//                 $("textarea[name='contents']").val("");
//                 scheduleModalSend.off('click');
//
//                 scheduleModalSend.on('click', ()=> {
//                     this.disabled = true;
//
//                     const title = $("input[name='title']").val();
//                     const contents = $("textarea[name='contents']").val();
//                     const loginUserId = "[[${userNam}]]";
//
//                     const scheduleInfo = {
//                         "user" : loginUserId,
//                         "title" : title,
//                         "contents": contents,
//                         "startDate" : moment(startDate).startOf('day').format(),
//                         "endDate" : moment(endDate).endOf('day').format(),
//                     }
//                     if(saveScheduleCheck(scheduleInfo)){
//                         $.ajax( {
//                             url:"/attend/saveSchedule",
//                             type: "post",
//                             contentType: "application/json",
//                             data: JSON.stringify(scheduleInfo),
//                             success: function (data){
//                                 if(data && data.id != null) {
//                                     alert("일정이 추가되었습니다.");
//
//                                     const newEvent = {
//                                         id: data.id,
//                                         title: '[일정]' + data.user.name + '님',
//                                         start : data.startDate,
//                                         end: data.endDate,
//                                         textColor: 'white',
//                                         color: "rgba( 35, 184, 116)",
//                                         extendedProps: {
//                                             title: data.title,
//                                             contents: data.contents
//                                         }
//                                     };
//                                     calendar.addEvent(newEvent);
//                                     modalOpen.style.display = "none";
//                                     modalOpen.style.visibility = "hidden";
//                                 } else {
//                                     alert ("일정 추가 중 오류가 발생했습니다.");
//                                     this.disabled = false;
//                                 }
//                             },
//                             error: function(e){
//                                 alert("일정 추가 요청 중 오류가 발생했습니다.");
//                                 this.disabled = false;
//                             },
//                         });
//                     }
//                 });
//             });
//         })
//     }
// }

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        height: 500,
    });
    calendar.render();
});






