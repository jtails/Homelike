package mx.jtails.homelike.util;

import android.content.Context;
import android.support.v4.app.Fragment;

import mx.jtails.homelike.R;

/**
 * Created by GrzegorzFeathers on 12/1/14.
 */
public class HomeLikeConfiguration {

    private static final UIConfiguration defaultConfiguration = UIConfiguration.CLIENT;

    private static UIConfiguration currentConfiguraiton =
            UIConfiguration.values()[HomelikePreferences.loadInt(
                    HomelikePreferences.APP_CONFIG, UIConfiguration.CLIENT.ordinal())];

    public static void setConfiguration(UIConfiguration uiConfiguration){
        currentConfiguraiton = uiConfiguration;
        HomelikePreferences.saveInt(HomelikePreferences.APP_CONFIG, uiConfiguration.ordinal());
    }

    public static void restoreConfiguration(){
        currentConfiguraiton = defaultConfiguration;
        HomelikePreferences.saveInt(HomelikePreferences.APP_CONFIG, defaultConfiguration.ordinal());
    }

    public static HomeMenuOption getDefaultContent(){
        return currentConfiguraiton.getDefaultContent();
    }

    public static HomeMenuOption[] getMenuOptions(){
        return currentConfiguraiton.getMenuOptions();
    }

    public static UIConfiguration getCurrentConfiguraiton(){
        return currentConfiguraiton;
    }

    public enum UIConfiguration {

        CLIENT(R.string.client_config, HomeClientMenuOption.defaultContent, HomeClientMenuOption.values()),
        PROVIDER(R.string.provider_config, HomeProviderMenuSection.defaultContent, HomeProviderMenuSection.values());

        private int mUserVisibleName;
        private HomeMenuOption mDefaultContent;
        private HomeMenuOption[] mOptions;

        private UIConfiguration(int userVisibleName, HomeMenuOption defaultContent,
                                HomeMenuOption[] options){
            this.mUserVisibleName = userVisibleName;
            this.mDefaultContent = defaultContent;
            this.mOptions = options;
        }

        private HomeMenuOption getDefaultContent() {
            return this.mDefaultContent;
        }

        private HomeMenuOption[] getMenuOptions() {
            return this.mOptions;
        }

        public static CharSequence[] asCharSequences(Context ctx){
            UIConfiguration[] configs = UIConfiguration.values();
            CharSequence[] sequences = new CharSequence[configs.length];
            for(int i = 0 ; i < configs.length ; i++){
                sequences[i] = ctx.getString(configs[i].mUserVisibleName);
            }
            return sequences;
        }

    }

    public interface HomeMenuOption {

        public Class<? extends Fragment> getFragmentClass();

        public int getIconRes();

        public int getSubtitleRes();

    }

}
