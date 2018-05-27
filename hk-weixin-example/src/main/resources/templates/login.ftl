<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <title>登 陆</title>
    <style type="text/css">
        .error-info {
            color: red;
        }

        .weixin-icon {
            width: 24px;
            height: 23px;
            display: block;
            background: url("/resources/images/icon24_appwx_logo.png");
            margin: 0 auto;
            position: absolute;
            left: 100px;
        }
    </style>
    <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

    <script>
        <!-- https://natapp.cn -->
        $(function () {

            wx.config({
                debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: '${jsapiTicket.appId}', // 必填，公众号的唯一标识
                timestamp: '${jsapiTicket.timestamp}', // 必填，生成签名的时间戳
                nonceStr: '${jsapiTicket.nonceStr}', // 必填，生成签名的随机串
                signature: '${jsapiTicket.signature}',// 必填，签名
                jsApiList: [ 'checkJsApi','scanQRCode',
                    'openCard' ]
            });
            $("#qucode").bind("chick",function(){
                alert(1);
                wx.scanQRCode({
                    needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
                    //            scanType: ["qrCode", "barCode"], // 可以指定扫二维码还是一维码，默认二者都有
                    success: function (res) {
                        var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
                        alert("result:" + result);
                    },
                    fail: function (res) {
                        alert("fail");
                        alert(JSON.stringify(res));
                    }
                });
            });
        });

        wx.error(function (res) {
            alert(res.errMsg);
        });
        //
        //    function qrcode() {
        //        wx.scanQRCode({
        //            needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
        ////            scanType: ["qrCode", "barCode"], // 可以指定扫二维码还是一维码，默认二者都有
        //            success: function (res) {
        //                var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
        //                alert("result:" + result);
        //            },
        //            fail: function (res) {
        //                alert("fail");
        //                alert(JSON.stringify(res));
        //            }
        //        });
        //    }
    </script>
</head>
<body>
<div>
    <h1>
        <em>Management System</em>
    </h1>
</div>
<div>
    <button id="chooseImage">chooseImage</button>
    <button id="checkJsApi">checkJsApi</button>
    <button id="qucode">扫一扫</button>
</div>

</body>
</html>