import {NativeModules, Platform} from 'react-native';

const CommonIntents  = NativeModules.RNIntents;


const noIOS = () => console.warn('Native Common intents cannot be used for iOS.');

const iosOS = {
    ImagePicker: noIOS,
    openWebPage: noIOS,
    openSettings:noIOS,
    openDateSettings: noIOS,
    openWifiSettings: noIOS,
    openBluetoothSettings: noIOS,
    openAirplaneModeSettings: noIOS,
    performWebSearch: noIOS,
    dialNumber : noIOS,
    startTimer : noIOS,
    createNote : noIOS
};

const Intents = Platform.OS === 'ios' ? iosOS : {

    ImagePicker : CommonIntents.ImagePick,
    openWeb: url => {
        if(url==null || url==""){
            throw new Error('Please enter right url');
        }
        CommonIntents.openWebPage(url);
    },
    openSettings: CommonIntents.openSettings,
    openDateSettings : CommonIntents.openDateSettings,
    openWifiSettings : CommonIntents.openWifiSettings,
    openBluetoothSettings : CommonIntents.openBluetoothSettings,
    openAirplaneModeSettings : CommonIntents.openAirplaneModeSettings,
    dialNumber : phoneno => {
        if(phoneno=="" || phoneno==null){
            throw new Error("Please enter phone number");
        }
        CommonIntents.dialNumber(phoneno);
    },
    
    startTimer : CommonIntents.startTimer,
    createAlarm : (message,hour,minutes) => {
        if(message==""){
            throw new Error("Message cannot be put as an empty");
        }
        CommonIntents.createAlarm(message,hour,minutes);
    },
    performWebSearch : query => {
        if(query== ""){
            throw new Error("Please Enter Text to search");
        }
        CommonIntents.performWebSearch(query);
    },
    createNote : (subject,text) => {
        if(subject == "" || text==""){
            throw new Error("You need to pass parameters");
        }
        CommonIntents.createNote(subject,text);
    }

}
export default Intents;
