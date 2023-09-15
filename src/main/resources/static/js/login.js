$(document).ready(function(){
    $('#login-form').submit(async function (event) {   //폼이 제출되었을 때
        event.preventDefault(); // 기본 제출 동작 막기
        const formData = new FormData(this);
        console.log(formData)

        $.ajax({
            type: 'POST', // POST 방식으로 전송
            url: '/member/login/login', // 데이터를 주고받을 URL
            data: formData, // 폼 데이터 전송
            processData: false, // 데이터를 처리하지 않도록 설정
            contentType: false, // 컨텐츠 타입을 false로 설정
            dataType: 'json', // 서버 응답을 JSON으로 예상
            success: function (data) {
                console.log(data)
            },
            error: function (error) {
                console.error('에러 발생:', error);
            }
        });
    });
});
