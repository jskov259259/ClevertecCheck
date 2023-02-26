package service.user;

import dao.user.UserDao;
import model.User;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.StringWriter;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User get(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public void post(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void put(User user) {
        userDao.update(user);
    }

    public String getXML(Integer id) {

        User user = userDao.getUserById(id);
        String xmlContent = "";
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(user, sw);
            xmlContent = sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlContent;
    }
}
