# SecurityProvider
Helps you develop an app with security

# Installation
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

//add this to your dependencies
implementation 'com.github.alimodares2003:SecurityProvider:2.0.0'
```
# How to use

To prevent duplication of your application, simply give your MD5 package name string a isClone function and check that the copy has been taken or not.
like this (my package name is ir.adp) : 
```kotlin
val bol = SPUtil.isClone(this,"8D50F686F6C848F2CC8E7DDBCB98E8DA")
if(bol)
  // is cloned
else
  // not cloned
```
