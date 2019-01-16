
package com.jose2007kj.snapkit;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.snapchat.kit.sdk.SnapLogin;
import android.util.Log;
import com.snapchat.kit.sdk.core.controller.LoginStateController;
import android.support.annotation.Nullable;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.snapchat.kit.sdk.login.models.MeData;
import com.snapchat.kit.sdk.login.models.UserDataResponse;
import com.snapchat.kit.sdk.login.networking.FetchUserDataCallback;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
public class RNSnapchatLoginModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private SharedPreferences prefs;
  
    
   
  public RNSnapchatLoginModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    SnapLogin.getLoginStateController(this.reactContext).addOnLoginStateChangedListener(mLoginStateChangedListener);
    prefs=PreferenceManager.getDefaultSharedPreferences(reactContext);
    
  }
  final LoginStateController.OnLoginStateChangedListener mLoginStateChangedListener =
            new LoginStateController.OnLoginStateChangedListener() {
              @Override
              public void onLoginSucceeded() {
                Log.w("RNSnapchatLogin","sucesslogged in");
                sendEvent(getReactApplicationContext(), "LoginSucceeded", null);
              }

              @Override
              public void onLoginFailed() {
                Log.w("RNSnapchatLogin","faildede login");
                sendEvent(getReactApplicationContext(), "LoginFailed", null);

              }

              @Override
              public void onLogout() {
                sendEvent(getReactApplicationContext(), "LogOut", null);

              }
            };
  public void sendEvent(ReactApplicationContext reactContext,
                        String eventName,
                        @Nullable WritableMap params) {
    reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit(eventName, params);
}
  @ReactMethod
  public void AddChangedLogin() {
    SnapLogin.getAuthTokenManager(getReactApplicationContext()).startTokenGrant();
    
}
  // final LoginStateController.OnLoginStateChangedListener mLoginStateChangedListener =
  //     new LoginStateController.OnLoginStateChangedListener() {
  //       @Override
  //       public void onLoginSucceeded() {
  //           // final WritableMap map1 = Arguments.createMap();   
  //           // map1.putString("snaponclick", "sucess");                    
  //           String query = "{me{displayName}}";
  //           String variables = null;
  //           SnapLogin.fetchUserData(getReactApplicationContext(), query, null, new FetchUserDataCallback() {
  //               @Override
  //               public void onSuccess(@Nullable UserDataResponse userDataResponse) {
  //                 // final WritableMap map = Arguments.createMap();   
  //                 if (userDataResponse == null || userDataResponse.getData() == null) {
  //                   prefs.edit().putString("username","not_set").commit();
  //                   return;
  //                   // map.putString("snapUsername", "medata is null");
                                  
  //                   // promise.resolve(map);
  //                   }

  //                   MeData meData = userDataResponse.getData().getMe();
  //                   if (meData == null) {
  //                     prefs.edit().putString("username","not_set").commit();
  //                     return;
  //                     // map.putString("snapUsername", "medata is not null");
                                  
  //                     // promise.resolve(map);
  //                   }
  //                   prefs.edit().putString("username",userDataResponse.getData().getMe().getDisplayName()).commit();
  //                   // map.putString("snapUsername", userDataResponse.getData().getMe().getDisplayName());
                                  
  //                   // promise.resolve(map);
  //                   // mNameTextView.setText(userDataResponse.getData().getMe().getDisplayName());

  //                   // if (meData.getBitmojiData() != null) {
  //                   //     Glide.with(getContext())
  //                   //             .load(meData.getBitmojiData().getAvatar())
  //                   //             .into(mBitmojiImageView);
  //                   // }
  //           }

  //           @Override
  //           public void onFailure(boolean isNetworkError, int statusCode) {
  //             prefs.edit().putString("username","login_failure").commit();
  //             // final WritableMap map = Arguments.createMap(); 
  //             // map.putString("snapUsername", "failure");
                                  
  //             //       promise.resolve(map);  
  //           }
  //   });
  //       // promise.resolve(map);
  //           // Here you could update UI to show login success
  //       }

  //       @Override
  //       public void onLoginFailed() {
  //         prefs.edit().putString("username","login_failure").commit();                
  //         // promise.resolve(map);
  //           // Here you could update UI to show login failure
  //       }

  //       @Override
  //       public void onLogout() {
  //           // Here you could update UI to reflect logged out state
  //       }
  //   };
  @ReactMethod
  public void isUserLoggedIn(Promise promise) {
    
    boolean isTrue = SnapLogin.isUserLoggedIn(getReactApplicationContext());
    String str = Boolean.toString(isTrue);
    // Log.w("Helloooooooooooooooooo", str);
    Log.d("RNSnapchatLogin","testing is user logged in"+str);
    promise.resolve(str);
}
  @Override
  public String getName() {
    return "RNSnapchatLogin";
  }
  @ReactMethod
  public void setToken(String accessToken) {
    // SnapLogin.getAuthTokenManager(getReactApplicationContext()).setAccessToken(accessToken);
    //promise.resolve(null);

}
  @ReactMethod
	public void snapOnClick(final Promise promise) {
    final WritableMap map = Arguments.createMap(); 
		try {
      // Add the LoginStateChangedListener you’ve defined to receive LoginInState updates
      SnapLogin.getAuthTokenManager(getReactApplicationContext()).startTokenGrant();
      // SnapLogin.getLoginStateController(getReactApplicationContext()).addOnLoginStateChangedListener(mLoginStateChangedListener);
//       String query = "{me{bitmoji{avtar},displayName}}";
//         String var = null;  // check the next line null if val is to be replaced somehow
//         try{
//         SnapLogin.fetchUserData(getReactApplicationContext(), query, null, new FetchUserDataCallback() {
//             @Override
//             public void onSuccess(@Nullable UserDataResponse userDataResponse) {
//                 if(userDataResponse==null || userDataResponse.getData() == null)
//                 {
//                   map.putString("snaponclick","response null" );
//                   promise.resolve(map); 
//                 }

//                 MeData meData = userDataResponse.getData().getMe();
//                 if(meData==null)
//                 {
//                   map.putString("snaponclick","data null" );
//                   promise.resolve(map); 
//                 }
//                 map.putString("snaponclick", userDataResponse.getData().getMe().getDisplayName());
//                 promise.resolve(map); 
                
//             }

//             @Override
//             public void onFailure(boolean b, int i) {
//               map.putString("snaponclick","failure");
//                 promise.resolve(map); 
//             }
// });
//         }catch(Exception e){
//           map.putString("snaponclick", e.toString());
//         }
      map.putString("snaponclick", "sucess");
      promise.resolve(map); 

    } catch (Exception e) {
      map.putString("snaponclick", e.toString());
                          
      promise.resolve(map);     
    }
  }
  @ReactMethod
  public void fetchUserData(final Promise promise) {
    String query = "{me{bitmoji{avtar},displayName}}";//"{me{displayName}}";
    String variables = null;
    Log.w("inside fech user",Boolean.toString(SnapLogin.isUserLoggedIn(this.reactContext)));
    if(SnapLogin.isUserLoggedIn(getReactApplicationContext()))
      {
        SnapLogin.fetchUserData(getReactApplicationContext(),"{me{displayName}}",null,new FetchUserDataCallback() {
              @Override
              public void onSuccess(@Nullable UserDataResponse userDataResponse) {
                Log.w("assd","CODEEEEEEEEEEEEEEEE");
                if (userDataResponse == null || userDataResponse.getData() == null) {
                  return;
                }

                MeData meData = userDataResponse.getData().getMe();
                if (meData == null) {
                  return;
                }

                promise.resolve(userDataResponse.getData().getMe().getDisplayName());
              }

              @Override
              public void onFailure(boolean b, int i) {
                String B = Boolean.toString(b);
                String I = Integer.toString(i);
                Log.w(B,"NETTWORKKKKKKKKKKKk");

                promise.reject(I);
              }
            }); }
    
} 
@ReactMethod
  public void AddStartLogin() {
    final LoginStateController.OnLoginStartListener mLoginStartListener =
            new LoginStateController.OnLoginStartListener() {
              @Override
              public void onLoginStart() {
                Log.w("NETTWORKKKKKKKKKKKk","starting di dong");

                sendEvent(getReactApplicationContext(), "Login Start", null);
              }
            };

    SnapLogin.getLoginStateController(getReactApplicationContext()).addOnLoginStartListener(mLoginStartListener);
}
  @ReactMethod
  public void snapFetch(final Promise promise){
   
        final WritableMap map = Arguments.createMap();
        try{
            map.putString("username", prefs.getString("username",""));
            promise.resolve(map);
        }catch(Exception e){
            
            map.putString("username", e.toString());
            promise.reject("COULD_NOT_FETCH", map.toString());
        }

  }
}


