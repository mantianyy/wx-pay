// index.js
// 获取应用实例
const app = getApp()

Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    canIUseGetUserProfile: false,
    canIUseOpenData: wx.canIUse('open-data.type.userAvatarUrl') && wx.canIUse('open-data.type.userNickName'), // 如需尝试获取用户信息可改为false
  },
  // 事件处理函数
  bindViewTap() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad() {
    if (wx.getUserProfile) {
      this.setData({
        canIUseGetUserProfile: true
      })
    }
      let _this=this;
      wx.login({
          success(res) {
              if (res.code) {
                  console.log("res.code is ",res.code)
                  // 发起网络请求
                  wx.request({
                      url: 'https://api.weixin.qq.com/sns/jscode2session',
                      data: {
                          appid:"wx6f8b36de02f6af65",  //开发者appid
                          secret:"f2054aa12404c43094e4b541b3d4426d", //开发者AppSecret(小程序密钥)
                          grant_type:"authorization_code",  //默认authorization_code
                          js_code: res.code    //wx.login登录获取的code值
                      },
                      success(res) {
                          console.log("openid结果呢",res)
                          res.data.openid;
                          res.data.session_key;
                      }
                  })
              } else {
                  console.log('登录失败！' + res.errMsg)
              }

          }
      })


      wx.requestPayment
      (
          {
              "timeStamp": "1681724711",
              "nonceStr": "akg8kc7nqcsqgs3hpd256bpokwyor1mw",
              "package": "prepay_id=wx171745091790433084432b979c66600000",
              "signType": "RSA",
              "paySign": "Jr4YDu8ikcn8vAnZI+SMOB74g9Lt8JJ2YvBlJI7HgMNrRmU29e8rhQo+hGC6z5hS3fW8+28+c/cmMZVKFT7C/VbH+yfM4yaRNQlPp39eu1Wnkyke0guOnBShHTo0CQEmRValVMfiqL5MCQTSegOIDL7/GHzHxUo1x65QWKF9g/alkrb6P1+mOq41tlIUeLVYlu6Zu/KZa8RHhuyrEiAIyXImbjm0UySPdyH1bdA4YUH2hZmikv+YW4CN+B6bwRGrWHBUO1eLikxQWteWwE47Z9uQCADn/InOgCF3dIz9EsOOKawAu07phGh9ZY88vChyQ4C+Q8v31VqHgmWb2mP8yw==",
              "success":function(res){
                  console.log("预下单成功",res)
              },
              "fail":function(res){
                  console.log("预下单失败",res)
              },
              "complete":function(res){
                  console.log("预下单结束",res)
              }
          }
      )

  },
  getUserProfile(e) {
    // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认，开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
    wx.getUserProfile({
      desc: '展示用户信息', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        console.log(res)
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    })
  },
  getUserInfo(e) {
    // 不推荐使用getUserInfo获取用户信息，预计自2021年4月13日起，getUserInfo将不再弹出弹窗，并直接返回匿名的用户个人信息
    console.log(e)
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  }
})
