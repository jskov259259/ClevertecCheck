## Reflection Home Task
Readme по заданию reflection

### Описане
1. В приложении реализованы кэши, с алгоритмами LRU и LFU в виде классов 
   [UserDaoCacheLRU](https://github.com/jskov259259/ClevertecCheck/blob/feature/cache/app/src/main/java/utils/cache/UserDaoCacheLRU.java)
   и [UserDaoCacheLFU](https://github.com/jskov259259/ClevertecCheck/blob/feature/cache/app/src/main/java/utils/cache/UserDaoCacheLFU.java) соотвественно.
2. Алгоритм и максимальный размер коллекции читатются из файла 
   [resources/application.yml](https://github.com/jskov259259/ClevertecCheck/blob/feature/cache/app/src/main/resources/application.yml):
   
   Пример:
   
   algorithm: LFU
   
   size: 6
3. Коллекция инициализируется через фабрику 
   [UserDaoCacheFactory](https://github.com/jskov259259/ClevertecCheck/blob/feature/cache/app/src/main/java/utils/cache/UserDaoCacheFactory.java).
   Здесь же считываются параметры кэша и максимальный размер коллекции.
4. Код содержит документариющие комментарии javadoc.
5. Кэши покрыты тестами: 
   [UserDaoCacheLFUTest](https://github.com/jskov259259/ClevertecCheck/blob/feature/cache/app/src/test/java/utils/cache/UserDaoCacheLFUTest.java)
   и [UserDaoCacheLRUTest](https://github.com/jskov259259/ClevertecCheck/blob/feature/cache/app/src/test/java/utils/cache/UserDaoCacheLRUTest.java)
6. Создан entity [User](https://github.com/jskov259259/ClevertecCheck/blob/feature/cache/app/src/main/java/model/User.java),
   с полем email, проверяемым через regex
7. В приложении реализованы слои dao и service с CRUD операциями. 
   [UserServiceImpl](https://github.com/jskov259259/ClevertecCheck/blob/feature/cache/app/src/main/java/service/user/UserServiceImpl.java)
   и [UserDaoImpl](https://github.com/jskov259259/ClevertecCheck/blob/feature/cache/app/src/main/java/dao/user/UserDaoImpl.java),
   работа организована через
   интерфейсы. Также UserServiceImpl содержит метод getXML(), возвращающий entity в формате XML.
8. Результат работы dao UserDaoImpl синхронизируется с кэшем через прокси
   [UserDaoCacheProxy](https://github.com/jskov259259/ClevertecCheck/blob/feature/cache/app/src/main/java/utils/cache/UserDaoCacheProxy.java)
   с помощью aspectJ. Релазиованы CRUD операции: GET, POST, DELETE, PUT.

Принцип работы прокси:
UserDaoCacheProxy с помощью аннотаций @Around перехватывает вызовы к методам UserDaoImpl и изменяет их поведение, 
синхронизируя раоту через кэш. Кэш инициализируется через фабрику.
