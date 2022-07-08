<h3 align="center">1. Single-responsibility principle</h3>
Классы разделены на пакеты для логического разделения.  
Класс Goods хранит все, что связано с товарами.  https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/goods/Goods.java#L5  
Класс Order позволяет создавать заказы.  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/shops/Order.java#L9  
Класс User хранит данные пользователя.  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/users/User.java#L3  
Классы ***Shop создают и хранят информацию о магазинах.  

https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/shops/FruitsShop.java#L9  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/shops/DevicesShop.java#L9  

<h3 align="center">2. Open-Closed principle:</h3>
При помощи использования общего интерфейса UserIF  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/users/UserIF.java#L3  
создаем пользователей разного типа:  
User - обычный пользователь  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/shops/DevicesShop.java#L293  
VipUser - пользователь с расширенным функционалом  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/users/VipUser.java#L5  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/users/VipUser.java#L6  
При помощи использования общего интерфейса ShopIF создаем магазины разного типа:  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/shops/ShopIF.java#L6  
DevicesShop и FruitsShop - каждый со своей логикой.  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/AppDevices.java#L10  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/AppFruits.java#L10  

<h3 align="center">3. Liskov substitution principle</h3>

VipUser наследник класса User, расширяющий возможности своего предка.  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/users/VipUser.java#L5    

<h3 align="center">4. Interface segregation principle</h3>

Класс DevicesShop имплементирует 2 интерфейса:  
ShopIF (базовые методы для магазинов) и ShopFeaturesIF (дополнительные методы магазинов).  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/shops/ShopIF.java#L6  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/shops/ShopFeaturesIF.java#L3  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/shops/DevicesShop.java#L9  
Класс FruitsShop имплементирует лишь базовые методы, так как дополнительные методы этому магазину не нужны.  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/shops/FruitsShop.java#L9

<h3 align="center">5. Dependency inversion principle</h3>
Независимо от того какую реализацию ShopIF мы подставим в строку 10 классов App***, мы получим работающую программу, отличающуюся лишь деталями реализаций этого интерфейса:
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/AppDevices.java#L10  
https://github.com/bulatka/HomeworkMarketplace/blob/557e5e1473a01965e44e239eadd85c9e6fccee42/src/main/java/AppFruits.java#L10  
