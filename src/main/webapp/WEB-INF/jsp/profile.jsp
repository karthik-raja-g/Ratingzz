<html>
<head>
    <title>Profile</title>
</head>
<style>
 body {
            margin: 0 auto;
            padding: 0;
            background-image: url("/rating_app/resources/img/homeImage.jpg");
        }

        .left {
            left: 100px;
        }

        .right {
            right: 25px;
        }

        .center {
            text-align: center;
        }

        .bottom {
            position: absolute;
            bottom: 25px;
        }

        #gradient {
            background: #999955;
            background-image: ;
            margin: 0 auto;
            margin-top: 100px;
            width: 100%;
            height: 150px;
        }

            #gradient:after {
                content: "";
                position: absolute;
                background: #E9E2D0;
                left: 50%;
                margin-top: -67.5px;
                margin-left: -270px;
                padding-left: 20px;
                border-radius: 5px;
                width: 520px;
                height: 275px;
                z-index: -1;
            }

        #card {
            position: absolute;
            width: 450px;
            height: 225px;
            padding: 25px;
            padding-top: 0;
            padding-bottom: 0;
            left: 50%;
            top: 67.5px;
            margin-left: -250px;
            background: #E9E2D0;
            box-shadow: -20px 0 35px -25px black, 20px 0 35px -25px black;
            z-index: 5;
        }

            #card img {
                width: 150px;
                float: left;
                border-radius: 5px;
                margin-right: 20px;
                /*-webkit-filter: sepia(1);
                -moz-filter: sepia(1);
                filter: sepia(1);*/
            }

            #card h2 {
                font-family: courier;
                color: #333;
                margin: 0 auto;
                padding: 0;
                font-size: 15pt;
            }

            #card p {
                font-family: courier;
                color: #555;
                font-size: 13px;
            }

            #card span {
                font-family: courier;
            }
</style>
<body>
    <c:set var = "employee" scope = "session" value = "${employeeInfo}"/>
    <div id="gradient"></div>
    <div id="card">
            <img src="/rating_app/resources/img/profile.png" />
            <h2>${employee.name}</h2>
        <p>Employee Id : ${employee.id}</p>
        <p>Role : ${employee.role}</p>
        <p>Email Id : ${employee.email}</p>
        <p>Date joined : ${employee.createdDate}</p>
        <p>Reporting person : ${employee.reportingPerson}</p>

        <span class="left bottom">Mobile : 97237 72***</span>
        <button onclick="location.href='employee-home';" class="button button1" > back </button>
    </div>

</body>
</html>
