对于 Base64 终于不用引用第三方包了，使用 java 库就可以完成

~~~java
// 编码
final String encoded = Base64.getEncoder().encodeToString( text.getBytes( StandardCharsets.UTF_8 ) );
// 解码
final String decoded = new String( Base64.getDecoder().decode( encoded ),StandardCharsets.UTF_8 );
~~~