
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
    //snapkit code

    final LoginStateController.OnLoginStateChangedListener mLoginStateChangedListener =
    new LoginStateController.OnLoginStateChangedListener() {
      @Override
      public void onLoginSucceeded() {
        Log.d("RNSnapchatLogin","sucesslogged in");
        sendEvent(getReactApplicationContext(), "LoginSucceeded", null);
      }

      @Override
      public void onLoginFailed() {
        Log.d("RNSnapchatLogin","faildede login");
        sendEvent(getReactApplicationContext(), "LoginFailed", null);

      }

      @Override
      public void onLogout() {
        sendEvent(getReactApplicationContext(), "LogOut", null);

      }
      
    };


    //ends here
    SnapLogin.getLoginStateController(this.reactContext).addOnLoginStateChangedListener(mLoginStateChangedListener);
    prefs=PreferenceManager.getDefaultSharedPreferences(reactContext);
    
  }
   public void sendEvent(ReactApplicationContext reactContext,
                        String eventName,
                        @Nullable WritableMap params) {
                          Log.d("RNSnapchatLogin","inside sendevent");
    reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit(eventName, params);
}
  @ReactMethod
  public void AddChangedLogin(final Promise promise) {
    Log.d("RNSnapchatLogin","inside AddChangedLogin");
    try{
      SnapLogin.getAuthTokenManager(getReactApplicationContext()).startTokenGrant();
      promise.resolve(true);
    }catch(Exception e){
      promise.resolve(false);
      Log.d("RNSnapchatLogin","inside AddChangedLogin erro "+e.toString());
      

    }
    
}

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
  public void setToken(String accessToken, Promise promise) {
    // try{
    //   SnapLogin.getAuthTokenManager(getReactApplicationContext()).setAccessToken(accessToken);
    //   promise.resolve(true);
    // }
    // catch(Exception e){
    //   Log.d("RNSnapchatLogin set token",e.toString());
    //   promise.resolve(false);
    // }

}

  @ReactMethod
  public void fetchUserData(final Promise promise) {
    String query = "{me{bitmoji{avtar},displayName}}";//"{me{displayName}}";
    String variables = null;
    Log.d("assd","inside fetch userdata");
    Log.d("inside fech user",Boolean.toString(SnapLogin.isUserLoggedIn(this.reactContext)));
    if(SnapLogin.isUserLoggedIn(getReactApplicationContext()))
      {
        SnapLogin.fetchUserData(getReactApplicationContext(),"{me{displayName}}",null,new FetchUserDataCallback() {
              @Override
              public void onSuccess(@Nullable UserDataResponse userDataResponse) {
                Log.d("assd","CODEEEEEEEEEEEEEEEE");
                if (userDataResponse == null || userDataResponse.getData() == null) {
                  Log.w("assd","null");
                  return;
                }

                MeData meData = userDataResponse.getData().getMe();
                if (meData == null) {
                  Log.w("assd","null");
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
  public void AddStartLogin(Promise promise) {
    try{
    final LoginStateController.OnLoginStartListener mLoginStartListener =
            new LoginStateController.OnLoginStartListener() {
              @Override
              public void onLoginStart() {
                Log.d("NETTWORKKKKKKKKKKKk","starting di dong");

                sendEvent(getReactApplicationContext(), "Login Start", null);
              }
            };

    SnapLogin.getLoginStateController(getReactApplicationContext()).addOnLoginStartListener(mLoginStartListener);
    promise.resolve(true);
  }catch(Exception e){
    Log.d("AddStartLogin exception snapchat",e.toString());
    promise.resolve(false);
          }
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

