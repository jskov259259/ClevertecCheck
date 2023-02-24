package utils.cache;

import model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class UserDaoCacheProxy {

    private UserDaoCache cache;

    public UserDaoCacheProxy() {
        cache = new UserDaoCacheFactory().createCache();
    }

    @Around("execution(* dao.user.UserDaoImpl.getUserById(..))")
    public Object getUserByIdAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        Integer id = (Integer) joinPoint.getArgs()[0];
        User userFromCache = cache.getUserById(id);
        if (userFromCache == null) {
            User userFromDao = (User) joinPoint.proceed();
            cache.saveUser(userFromDao);
            return userFromDao;
        }
        return userFromCache;
    }

    @Around("execution(* dao.user.UserDaoImpl.save(..))")
    public void saveAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        User user = (User) joinPoint.getArgs()[0];
        joinPoint.proceed();
        cache.saveUser(user);
    }

    @Around("execution(* dao.user.UserDaoImpl.update(..))")
    public void updateAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        User user = (User) joinPoint.getArgs()[0];
        joinPoint.proceed();
        cache.updateUser(user);
    }

    @Around("execution(* dao.user.UserDaoImpl.delete(..))")
    public void deleteAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        User user = (User) joinPoint.getArgs()[0];
        joinPoint.proceed();
        cache.deleteUser(user);
    }

}