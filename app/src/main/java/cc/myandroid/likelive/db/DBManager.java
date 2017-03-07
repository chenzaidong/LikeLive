package cc.myandroid.likelive.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import cc.myandroid.likelive.model.bean.DaoMaster;
import cc.myandroid.likelive.model.bean.DaoSession;
import cc.myandroid.likelive.model.bean.Player;
import cc.myandroid.likelive.model.bean.PlayerDao;
import cc.myandroid.likelive.model.bean.User;
import cc.myandroid.likelive.model.bean.UserDao;
import cc.myandroid.likelive.utils.EncryptionUtil;

/**
 * Created by chenzd on 2017/2/23.
 * 数据库管理类
 * 单例模式
 */

public class DBManager {
    /**
     * 数据库名称
     */
    private static final String DB_NAME = "likelive_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    private DBManager(Context context) {
        openHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
    }

    /**
     * 对象初始化
     * @param context
     */
    public static void init(Context context){
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
    }

    public static DBManager getInstance() {
        return mInstance;
    }

    /**
     * 获取可写数据库
     *
     * @return
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        }
        return openHelper.getWritableDatabase();
    }

    /**
     * 获取可读数据库
     *
     * @return
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        }
        return openHelper.getReadableDatabase();
    }

    /**
     * 插入一条用户信息
     * @param user
     * @return 插入的ID
     */
    public long insertUser(User user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        return userDao.insert(user);
    }

    /**
     * 删除一条User数据
     *
     * @param user
     */
    public void deleteUser(User user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.delete(user);
    }

    /**
     * 更新一条记录
     *
     * @param user
     */
    public void updateUser(User user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.update(user);
    }

    /**
     * 查询所有用户列表
     *
     * @return
     */
    public List<User> queryUserList( ) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> userQueryBuilder = userDao.queryBuilder();
        List<User> userList = userQueryBuilder.list();
        return userList;
    }

    /**
     * 通过手机号码查询用户
     *
     * @param phoneNum
     * @return
     */
    public List<User> quereUserList(String phoneNum) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> userQueryBuilder = userDao.queryBuilder();
        userQueryBuilder.where(UserDao.Properties.PhoneNumber.eq(phoneNum));
        List<User> list = userQueryBuilder.list();
        return list;
    }

    /**
     * 通过手机号码查询用户
     *
     * @param id
     * @return
     */
    public List<User> quereUserList(Long id) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> userQueryBuilder = userDao.queryBuilder();
        userQueryBuilder.where(UserDao.Properties.Id.eq(id));
        List<User> list = userQueryBuilder.list();
        return list;
    }

    /**
     * 通过帐号及密码查询用户
     * @param phoneNum
     * @param password
     * @return
     */
    public List<User> quereUserList(String phoneNum,String password){
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> userQueryBuilder = userDao.queryBuilder();
        userQueryBuilder.where(UserDao.Properties.PhoneNumber.eq(phoneNum),UserDao.Properties.Password.eq(password));
        List<User> list = userQueryBuilder.list();
        return list;
    }


    /**
     * 插入一条关注的主播
     * @param player
     * @return 插入数据中数据库中的ID
     */
    public long insertPlayer(Player player) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PlayerDao playerDao = daoSession.getPlayerDao();
        return playerDao.insert(player);
    }

    /**
     * 删除一条记录
     *
     * @param player
     */
    public void deletePlayer(Player player) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PlayerDao playerDao = daoSession.getPlayerDao();
        playerDao.delete(player);
    }

    /**
     * 删除一条记录
     *
     */
    public void deletePlayer(String playerId) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PlayerDao playerDao = daoSession.getPlayerDao();
        QueryBuilder<Player> playerQueryBuilder = playerDao.queryBuilder();
        DeleteQuery<Player> where = playerQueryBuilder.where(PlayerDao.Properties.PlayerId.eq(playerId)).buildDelete();
        where.executeDeleteWithoutDetachingEntities();
    }


    /**
     * 删除一条记录
     *
     * @param player
     */
    public void updatePlayer(Player player) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PlayerDao playerDao = daoSession.getPlayerDao();
        playerDao.delete(player);
    }

    /**
     * 查询所有player对象
     *
     * @return
     */
    public List<Player> queryPlayerList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PlayerDao playerDao = daoSession.getPlayerDao();
        QueryBuilder<Player> playerQueryBuilder = playerDao.queryBuilder();
        List<Player> list = playerQueryBuilder.list();
        return list;
    }

    /**
     * 根据User_id查询所有player对象
     *
     * @return
     */
    public List<Player> queryPlayerList(Long userId) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PlayerDao playerDao = daoSession.getPlayerDao();
        QueryBuilder<Player> playerQueryBuilder = playerDao.queryBuilder();
        playerQueryBuilder.where(PlayerDao.Properties.UserId.eq(userId));
        List<Player> list = playerQueryBuilder.list();
        return list;
    }

    /**
     * 根据playerId查询所有player对象
     *
     * @return
     */
    public List<Player> queryPlayerList(String playerId) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PlayerDao playerDao = daoSession.getPlayerDao();
        QueryBuilder<Player> playerQueryBuilder = playerDao.queryBuilder();
        playerQueryBuilder.where(PlayerDao.Properties.PlayerId.eq(playerId));
        List<Player> list = playerQueryBuilder.list();
        return list;
    }
        //测试用
        //TODO 记得删除
        public static void insertUserTest(){
            User user = new User();
            user.setPhoneNumber("123");
            try {
                user.setPassword(EncryptionUtil.EncodeMD5("123"));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            DBManager.getInstance().insertUser(user);
        }
}
