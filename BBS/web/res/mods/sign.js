$(function(){
    // 输入账号匹配正则表达式
    $("#inputEmailOrPhoneNumber").on("blur",function () {
        var input = $("#inputEmailOrPhoneNumber").val();
        if(input.match(/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/) || input.match(/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/)){
            // 输入合法
        }else{
            // 输入不合法
        }
    })
    // 输入登录密码匹配正则表达式
    $("#inputLoginPassword").on("blur",function () {
        var input = $("#inputLoginPassword").val();
        if(input.match(/^[a-zA-Z0-9_-]{6,18}$/)){
            // 输入合法
        }else{
            // 输入不合法
        }
    })
    // 输入昵称是否合法
    $("#inputUsername").on("blur",function () {
        var input = $("#inputUsername").val();
        if(input !== null && input !== ""){
            // 输入合法
        }else{
            // 输入不合法
        }
    })

    // 输入注册密码匹配正则表达式
    $("#inputRegisterPassword").on("blur",function () {
        var input = $("#inputRegisterPassword").val();
        if(input.match(/^[a-zA-Z0-9_-]{6,18}$/)){
            // 输入合法
        }else{
            // 输入不合法
        }
    })

    // 输入确认密码是否合法
    $("#inputConfirmPassword").on("blur",function () {
        var password = $("#inputRegisterPassword").val();
        var confirmPassword = $("#inputConfirmPassword").val();
        if(password === confirmPassword){
            // 输入合法
        }else{
            // 输入不合法
        }
    })

    // 输入邮箱匹配正则表达式
    $("#inputEmail").on("blur",function () {
        var input = $("#inputEmail").val();
        if(input.match(/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/)){
            // 输入合法
        }else{
            // 输入不合法
        }
    })

    // 输入手机号匹配正则表达式
    $("#inputPhoneNumber").on("blur",function () {
        var input = $("#inputPhoneNumber").val();
        if(input.match(/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/)){
            // 输入合法
        }else{
            // 输入不合法
        }
    })



})