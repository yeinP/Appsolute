let saveMember = {
    init:function (){
        $("#btn-save-member").on("click", ()=>{
            this.save();
        })
    },
    save: function (){
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        $.ajax({
            type : "POST",
            url : "/member/memberJoinForm",
            data : JSON.stringify(data),
            datatype: "json",
            contentType: 'application/json'
        }).done(function (resp){
            alert("회원가입이 완료");
            console.log(data);
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
}
saveMember.init();