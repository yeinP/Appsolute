$(document).ready(function () {
    $('#login').click(function (e) {
        e.preventDefault(); // 기본 폼 제출 동작을 막습니다.
        // 폼 데이터를 가져옵니다.
        let emp_num = $('input[name="emp_num"]').val();
        let emp_password = $('input[name="emp_password"]').val();
        // 모든 오류 메시지 초기화
        $('#textError1').text('');
        $('#textError2').text('');
        // 필드 검증
        let error = false;
        if (!emp_num) {
            $('#textError1').text('반드시 입력해 주세요.').css('color', 'red');
            error = true;
        } else {
            $('#textError1').text('');
        }
        if (!emp_password) {
            $('#textError2').text('반드시 입력해 주세요.').css('color', 'red');
            error = true;
        } else {
            $('#textError2').text('');
        }
        if (!error){


            // 필드 검증 통과 시에만 AJAX 요청 수행
            $.ajax({
                type: 'POST',
                url: '/login/mypage', // 수정 처리를 수행하는 컨트롤러의 URL을 적어야 합니다.
                data: {
                    emp_num: emp_num,
                    emp_password: emp_password
                },
                success: function (data) {
                    window.location.href = "/login/mypage";
                },
                error: function () {
                    // AJAX 요청 실패 시 오류 메시지 표시
                    alert("정보가 일치하지 않습니다.")
                  //  document.getElementById("error-message").text('서버 오류. 다시 시도하세요.');
                }
            });
        }
    });
});