
package com.jose2007kj.reactnative;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.snapchat.kit.sdk.SnapLogin;
import com.snapchat.kit.sdk.core.controller.LoginStateController;
public class RNSnapchatLoginModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNSnapchatLoginModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNSnapchatLogin";
  }
  @ReactMethod
	public void snapOnClick(final Promise promise) {
    final WritableMap map = Arguments.createMap(); 
		try {

      SnapLogin.getAuthTokenManager(getReactApplicationContext()).startTokenGrant();
      map.putString("snaponclick", "sucess");
                          
      promise.resolve(map);
    } catch (Exception e) {
      map.putString("snaponclick", e.toString());
                          
      promise.resolve(map);     
    }
  }
  @ReactMethod
	public void snapSubscribe(final Promise promise){
    final WritableMap map = Arguments.createMap(); 
    try {
      final LoginStateController.OnLoginStateChangedListener mLoginStateChangedListener =
      new LoginStateController.OnLoginStateChangedListener() {
        @Override
        public void onLoginSucceeded() {
          map.putString("snaponsubscribe", "sucess");
                          
          promise.resolve(map);
            // Here you could update UI to show login success
        }

        @Override
        public void onLoginFailed() {
            // Here you could update UI to show login failure
        }

        @Override
        public void onLogout() {
            // Here you could update UI to reflect logged out state
        }
    };

    // Add the LoginStateChangedListener you’ve defined to receive LoginInState updates
    SnapLogin.getLoginStateController(getContext()).addOnLoginStateListener(mLoginStateChangedListener);

      
      
    } catch (Exception e) {
      map.putString("snaponsubscribe", e.toSring());
                          
      promise.resolve(map);    
    }
  }
  @ReactMethod
  public void snapFetch(final Promise promise){
    String query = "{me{bitmoji{avatar},displayName}}";
    String variables = null;
    SnapLogin.fetchUserData(this, query, variables, new FetchUserDataCallback() {
        @Override
        public void onSuccess(@Nullable UserDataResponse userDataResponse) {
            if (userDataResponse == null || userDataResponse.getData() == null) {
                return;
            }

            // MeData meData = userDataResponse.getData().getMe();
            // if (meData == null) {
            //     return;
            // }
            map.putString("snapUsername", userDataResponse.getData().getMe().getDisplayName());
                          
            promise.resolve(map);
            mNameTextView.setText(userDataResponse.getData().getMe().getDisplayName());

            // if (meData.getBitmojiData() != null) {
            //     Glide.with(getContext())
            //             .load(meData.getBitmojiData().getAvatar())
            //             .into(mBitmojiImageView);
            // }
        }

    @Override
    public void onFailure(boolean isNetworkError, int statusCode) {
      map.putString("snapUsername", failure);
                          
            promise.resolve(map);  
    }
});
  }
}

