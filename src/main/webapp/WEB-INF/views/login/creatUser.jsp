<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bootstrap Elegant Account Login Form with Avatar Icon</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        body {
            background-color: #ACB992;

            font-family: 'Varela Round', sans-serif;
            margin-top: 200px;
        }

        .form-control {
            box-shadow: none;
            border-color: #ddd;
        }

        .form-control:focus {
            border-color: #4aba70;
        }

        .login-form {
            width: 600px;
            margin: 0 auto;
            padding: 30px 0;
        }

        .login-form form {
            color: #434343;
            border-radius: 1px;
            margin-bottom: 15px;
            background: #fff;
            border: 1px solid #f3f3f3;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            padding: 30px;
        }

        .login-form h4 {
            text-align: center;
            font-size: 22px;
            margin-bottom: 20px;
        }

        .login-form .avatar {
            color: #fff;
            margin: 0 auto 30px;
            text-align: center;
            width: 100px;
            height: 100px;
            border-radius: 50%;
            z-index: 9;
            background: #ACB992;
            padding: 15px;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.1);
        }

        .login-form .avatar i {
            font-size: 62px;
        }

        .login-form .form-group {
            margin-bottom: 20px;
        }

        .login-form .form-control, .login-form .btn {
            min-height: 40px;
            border-radius: 2px;
            transition: all 0.5s;
        }

        .login-form .close {
            position: absolute;
            top: 15px;
            right: 15px;
        }

        .login-form .btn {
            background: #ACB992;
            border: none;
            line-height: normal;
        }

        .login-form .btn:hover, .login-form .btn:focus {
            background:#ACB992;
        }

        .login-form .checkbox-inline {
            float: left;
        }

        .login-form input[type="checkbox"] {
            margin-top: 2px;
        }

        .login-form .forgot-link {
            float: right;
        }

        .login-form .small {
            font-size: 18px;
            margin-top: 10px;
        }

        .login-form a {
            color:#464E2E;
        }
    </style>



    <script>
        //이메일 체크값임 onchange는 키보드이벤트로 키보드에서 손을때면 자동으로 검색
        function idCheckHandler(e) {
            console.log("key up")
            let user_id = e.value;

            $.ajax({
                url: "/getUserExistForAJAX",
                type: "post",
                dataType: "JSON",
                data: {
                    "user_id": user_id
                },
                success: function (res) {
                    let checkRes = res;
                    console.log(res);

                    if (checkRes === 2) {
                        alert("존재하는 이메일입니다. 다시 시도해주세요")
                    } else if (checkRes === 0) {
                        alert("오류입니다. 잠시 후 다시 시도해주세요.");
                    }
                }
            })
        }
    </script>
    <script>


        //비밀번호 유효성체크
        function check_pw() {

            if (document.getElementById('pw').value == document.getElementById('pw2').value) {
                document.getElementById('check').innerHTML = '비밀번호가 일치합니다.'
                document.getElementById('check').style.color = 'Black';
            } else {
                document.getElementById('check').innerHTML = '비밀번호가 일치하지 않습니다.';
                document.getElementById('check').style.color = 'Black';
                return false; //비밀번호가다르면 회원가입불가하게 막음
            }
        }

    </script>
</head>
<body>
<div class="login-form">
    <form action="/insertUser" method="post" onsubmit="return check_pw()">
        <div class="avatar"><i class="material-icons">&#xE7FF;</i></div>
        <h4 class="modal-title">Register</h4>
        <div class="form-group">
            <p>user_id</p>
            <input type="text" class="form-control" placeholder="Enter Username" required="required" name="user_id"
                   onkeyup="idCheckHandler(this)"  id="user_id">
        </div>
        <div class="form-group">
            <p>Username</p>
            <input type="text" class="form-control" placeholder="Enter Username" required="required" name="user_name"
                   id="name">
        </div>
        <div class="form-group">
            <p>Email</p>
            <input type="text" class="form-control" placeholder="Enter Email" required="required"
                    name="user_email" id="email">
        </div>
        <div class="form-group">
            <p>addr1</p>
            <input type="text" class="form-control" placeholder="Enter addr1" required="required" name="addr1"
                   id="addr1">
        </div>
        <div class="form-group">
            <p>addr2</p>
            <input type="text" class="form-control" placeholder="Enter addr2" required="required" name="addr2"
                   id="addr2">
        </div>
        <div class="form-group">
            <p>Password</p>
            <input type="password" class="form-control" placeholder="Enter Password" required="required" name="user_pw"
                   onchange="check_pw()" id="pw">
        </div>
        <div class="form-group">
            <p>Confirm Password</p>
            <input type="password" class="form-control" placeholder="Confirm Password" required="required"
                   onchange="check_pw()" id="pw2">
            <p id="check"></p>
        </div>

        <input type="submit" class="btn btn-primary btn-block btn-lg" value="Sign up">

        <div class="text-center small">Already have an account? <a href="/login">Login here</a></div>
        <div class="text-center small">Do you want to go back?<a href="/home">home</a></div>
    </form>

</div>
</body>
</html>