Zoho Portal CRM empowers your customers, vendors, and partners with online portals where
you can define what data is shown to whom and define their individual access
permissions. Enable personal buying by providing a platform where clients can view
your products and services, place orders, and update their own contact information.

This is a sample code implementing Zoho CRM Portal's mobile SDK. This project
contains code for initializing portal sdk and hitting a api through sdk. You may clone the whole
project to run the sample application.

## Initializing the SDK  

You must initialize the SDK before your app can consume its methods. To initialize, set the required configurations through the ZCRMSDKConfigs object and pass it to the init() method in the ZCRMSDKClient object as follows.

**ZCRMSDKClient.getInstance(applicationContext).init(
	configs: ZCRMSDKConfigs,
	initCallback: ZCRMSDKClient.Companion.ZCRMInitCallback
	)**

If the user is not logged in, a login screen will be prompted during the SDK initialization. Based on the result of the login, the appropriate callback method will be triggered.
Only after the successful initialization of the SDK, further methods can be invoked as intended.

## SDK Configuration object   

There are methods for configuring the app client details.

Set the app client details, the portal ID and portal name as string values, and pass it to the getBuilder() method (builder pattern) using the ZCRMSDKConfigs object as shown below.

**ZCRMSDKConfigs.getBuilder(
       clientID: "<OAuth client id of the app client registered in Zoho>",
       clientSecret: "<OAuth client secret of the app client registered in Zoho>",
       oauthScopes: "<required_oauthscopes>",
       portalId: "<portal_ID>",
       portalName: "<portal_name>"
            ).build()**

- **clientID and clientSecret** : Configure the OAuth client ID and secret of the app registered in Zoho.
- **oauthScopes** : The OAuth scopes of the Zoho CRM API that the app would use.
- **portalName** : Specify the client's portal name.
- **portalId** : Specify the client's portal ID.

Full documentation for the SDK can be found on our [website](https://www.zoho.com/crm/developer/docs/mobile-sdk/android.html). 
