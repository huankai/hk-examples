# namespace 名称空间， +语言名称 + 包名
namespace java com.hk.thrift

# 将 thrift 中的类型定义为 java 中的类型
typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String


# 定义数据结构体：
# 语法 : 顺序 optional|required 类型 参数名
# 如： 1: optional String username;
struct Person {
    1: optional String username;
    2: optional int age;
    3: optional boolean married;
}

# 定义异常
exception DataException {
    1: optional String message;
    2: optional String stack;
    3: optional String date; # 注意，thrift 中不支持日期类型

}

# 定义 service
service PersonService {

   /*
        定义方法,接受参数，并抛出异常
   */
    Person getByUsername(1: String username)throws (1: DataException dataException);

    void save(1:Person person) throws (1: DataException dataException);

}

