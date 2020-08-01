package africa.of.designers.churchill;

import android.app.Application;

import com.parse.Parse;

import africa.of.designers.churchill.di.component.AppComponent;
import africa.of.designers.churchill.di.component.DaggerAppComponent;
import africa.of.designers.churchill.di.modules.AppModule;

import co.paystack.android.PaystackSdk;
import dagger.Module;
import dagger.Provides;
import dagger.Component;


/**
 * Created by abdularis on 18/07/17.
 */

public class WordSearchApp extends Application {

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        PaystackSdk.initialize(getApplicationContext());
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
