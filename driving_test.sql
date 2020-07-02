/*
 Navicat Premium Data Transfer

 Source Server         : MYSQL80
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : driving_test

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 22/02/2020 12:53:52
*/
SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'driving_test' ORDER BY create_time DESC;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for adminer
-- ----------------------------
DROP TABLE IF EXISTS `adminer`;
CREATE TABLE `adminer`  (
  `ad_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ad_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ad_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ad_sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ad_phone` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ad_zaizhi` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ad_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adminer
-- ----------------------------
INSERT INTO `adminer` VALUES ('1234567', '程昱人', '1234567', '男', '11111111', '是');

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `a_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `a_que_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `a_select` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `a_se_neirong` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `a_yorn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`a_id`) USING BTREE,
  INDEX `a_que_id_fk`(`a_que_id`) USING BTREE,
  CONSTRAINT `a_que_id_fk` FOREIGN KEY (`a_que_id`) REFERENCES `question` (`q_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES ('00001', '00001', 'A', '正确', '是');
INSERT INTO `answer` VALUES ('00002', '00001', 'B', '错误', '否');
INSERT INTO `answer` VALUES ('00003', '00002', 'A', '正确', '否');
INSERT INTO `answer` VALUES ('00004', '00002', 'B', '错误', '是');
INSERT INTO `answer` VALUES ('00005', '00003', 'A', '违章行为', '否');
INSERT INTO `answer` VALUES ('00006', '00003', 'B', '违法行为', '是');
INSERT INTO `answer` VALUES ('00007', '00003', 'C', '过失行为', '否');
INSERT INTO `answer` VALUES ('00008', '00003', 'D', '违规行为', '否');
INSERT INTO `answer` VALUES ('00009', '00004', 'A', '刑事责任', '是');
INSERT INTO `answer` VALUES ('00010', '00004', 'B', '民事责任', '否');
INSERT INTO `answer` VALUES ('00011', '00004', 'C', '经济责任', '否');
INSERT INTO `answer` VALUES ('00012', '00004', 'D', '直接责任', '否');
INSERT INTO `answer` VALUES ('00013', '00005', 'A', '5年内', '否');
INSERT INTO `answer` VALUES ('00014', '00005', 'B', '10年内', '否');
INSERT INTO `answer` VALUES ('00015', '00005', 'C', '终生', '是');
INSERT INTO `answer` VALUES ('00016', '00005', 'D', '20年内', '否');
INSERT INTO `answer` VALUES ('00017', '00006', 'A', '产品合格标志', '否');
INSERT INTO `answer` VALUES ('00018', '00006', 'B', '保持车距标志', '否');
INSERT INTO `answer` VALUES ('00019', '00006', 'C', '提醒危险标志', '否');
INSERT INTO `answer` VALUES ('00020', '00006', 'D', '检验合格标志', '是');
INSERT INTO `answer` VALUES ('00021', '00007', 'A', '是', '否');
INSERT INTO `answer` VALUES ('00022', '00007', 'B', '否', '是');
INSERT INTO `answer` VALUES ('00023', '00008', 'A', '是', '否');
INSERT INTO `answer` VALUES ('00024', '00008', 'B', '是', '是');
INSERT INTO `answer` VALUES ('00025', '00009', 'A', '工作证', '否');
INSERT INTO `answer` VALUES ('00026', '00009', 'B', '驾驶证', '是');
INSERT INTO `answer` VALUES ('00027', '00009', 'C', '身份证', '否');
INSERT INTO `answer` VALUES ('00028', '00009', 'D', '职业资格证', '否');
INSERT INTO `answer` VALUES ('00029', '00010', 'A', '使用所学车型的教练车由教练员随车指导', '是');
INSERT INTO `answer` VALUES ('00030', '00010', 'B', '使用所学车型的教练车单独驾驶学习', '否');
INSERT INTO `answer` VALUES ('00031', '00010', 'C', '使用私家车由教练员随车指导', '否');
INSERT INTO `answer` VALUES ('00032', '00010', 'D', '使用所学车型的教练车由非教练员的驾驶人随车指导', '否');
INSERT INTO `answer` VALUES ('00033', '00011', 'A', '正确', '是');
INSERT INTO `answer` VALUES ('00034', '00011', 'B', '错误', '否');
INSERT INTO `answer` VALUES ('00035', '00012', 'A', '对', '否');
INSERT INTO `answer` VALUES ('00036', '00012', 'B', '错', '是');
INSERT INTO `answer` VALUES ('00037', '00013', 'A', '正确', '是');
INSERT INTO `answer` VALUES ('00038', '00013', 'B', '错误', '否');
INSERT INTO `answer` VALUES ('00039', '00014', 'A', '在从动轮下铺垫砂石', '否');
INSERT INTO `answer` VALUES ('00040', '00014', 'B', '换高速挡加速猛冲', '否');
INSERT INTO `answer` VALUES ('00041', '00014', 'C', '在驱动轮下铺垫砂石', '是');
INSERT INTO `answer` VALUES ('00042', '00014', 'D', '猛打转向盘配合急加速', '否');
INSERT INTO `answer` VALUES ('00043', '00015', 'A', '前后防雾灯', '否');
INSERT INTO `answer` VALUES ('00044', '00015', 'B', '危险报警闪光灯', '是');
INSERT INTO `answer` VALUES ('00045', '00015', 'C', '前大灯', '否');
INSERT INTO `answer` VALUES ('00046', '00015', 'D', '倒车灯', '否');
INSERT INTO `answer` VALUES ('00047', '00016', 'A', '下坡车让上坡车', '是');
INSERT INTO `answer` VALUES ('00048', '00016', 'B', '坡顶交会时距离坡顶远的一方让行', '否');
INSERT INTO `answer` VALUES ('00049', '00016', 'C', '上坡车让下坡车', '否');
INSERT INTO `answer` VALUES ('00050', '00016', 'D', '下坡车已行至中途而上坡车未上坡时，让上坡车', '否');
INSERT INTO `answer` VALUES ('00051', '00017', 'A', '靠路左侧，加速绕行', '否');
INSERT INTO `answer` VALUES ('00052', '00017', 'B', '停车瞭望，缓慢通过', '否');
INSERT INTO `answer` VALUES ('00053', '00017', 'C', '注意观察，尽快通过', '是');
INSERT INTO `answer` VALUES ('00054', '00017', 'D', '勤鸣喇叭，低速通行', '否');
INSERT INTO `answer` VALUES ('00055', '00018', 'A', '迅速抬上担架送往医院', '否');
INSERT INTO `answer` VALUES ('00056', '00018', 'B', '适当调整损伤时的姿势', '否');
INSERT INTO `answer` VALUES ('00057', '00018', 'C', '用绷带对骨折部位进行包扎', '否');
INSERT INTO `answer` VALUES ('00058', '00018', 'D', '不要移动身体骨折部位', '是');
INSERT INTO `answer` VALUES ('00059', '00019', 'A', '正确', '否');
INSERT INTO `answer` VALUES ('00060', '00019', 'B', '错误', '是');
INSERT INTO `answer` VALUES ('00061', '00020', 'A', '正确', '否');
INSERT INTO `answer` VALUES ('00062', '00020', 'B', '错误', '是');
INSERT INTO `answer` VALUES ('00063', '00021', 'A', '这是a', '是');
INSERT INTO `answer` VALUES ('00064', '00021', 'B', '这是b', '是');
INSERT INTO `answer` VALUES ('00065', '00021', 'C', '这是c', '是');
INSERT INTO `answer` VALUES ('00066', '00021', 'D', '这是d', '否');
INSERT INTO `answer` VALUES ('00067', '00022', 'A', '这是a选项', '是');
INSERT INTO `answer` VALUES ('00068', '00022', 'B', '这是b选项', '是');
INSERT INTO `answer` VALUES ('00069', '00022', 'C', '这是c选项', '是');
INSERT INTO `answer` VALUES ('00070', '00022', 'D', '这是d选项', '是');

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `c_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c_jiazhao` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c_miaoshu` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES ('01', '小车', 'C1/C2/C3', '包括小型汽车，小型自动型汽车，低速载货汽车，三轮汽车，残疾人专用汽车，无轨电车以及有轨电车');
INSERT INTO `car` VALUES ('02', '货车', 'A2/B2', '包括大型货车与牵引车');
INSERT INTO `car` VALUES ('03', '客车', 'A1/A3/B1', '包括大型客车，中型客车以及城市公交车');
INSERT INTO `car` VALUES ('04', '摩托车', 'D/E/F', '包括普通三轮摩托车，普通二轮摩托车，轻便摩托车');
INSERT INTO `car` VALUES ('05', '客运', '资格证', '考取客运资格证');
INSERT INTO `car` VALUES ('06', '货运', '资格证', '考取货运资格证');
INSERT INTO `car` VALUES ('07', '危险品', '资格证', '考取危险品资格证');
INSERT INTO `car` VALUES ('08', '教练', '资格证', '考取教练资格证');

-- ----------------------------
-- Table structure for drivesc
-- ----------------------------
DROP TABLE IF EXISTS `drivesc`;
CREATE TABLE `drivesc`  (
  `ds_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ds_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ds_jianjie` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ds_phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ds_address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ds_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drivesc
-- ----------------------------
INSERT INTO `drivesc` VALUES ('001', '路路通驾校', '无', '1234567', '新疆乌鲁木齐');
INSERT INTO `drivesc` VALUES ('002', '001驾校', '无', '3213311', '天津');
INSERT INTO `drivesc` VALUES ('003', '002驾校', '无', '4421234', '秦皇岛');
INSERT INTO `drivesc` VALUES ('004', '003驾校', '无', '1234312', '乌鲁木齐');
INSERT INTO `drivesc` VALUES ('005', '004驾校', '无', '1234567', '上海');
INSERT INTO `drivesc` VALUES ('006', '005驾校', '无', '1234567', '云南');
INSERT INTO `drivesc` VALUES ('007', '006驾校', '无', '1234567', '哈尔滨');
INSERT INTO `drivesc` VALUES ('008', '燕京理工驾校', '真TMD贵', '18630445202', '地球亚洲中国');

-- ----------------------------
-- Table structure for error
-- ----------------------------
DROP TABLE IF EXISTS `error`;
CREATE TABLE `error`  (
  `e_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `e_que_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `e_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `e_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`e_id`) USING BTREE,
  INDEX `e_user_id_fk`(`e_user_id`) USING BTREE,
  INDEX `e_que_id_fk`(`e_que_id`) USING BTREE,
  CONSTRAINT `e_que_id_fk` FOREIGN KEY (`e_que_id`) REFERENCES `question` (`q_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `e_user_id_fk` FOREIGN KEY (`e_user_id`) REFERENCES `usersb` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of error
-- ----------------------------

-- ----------------------------
-- Table structure for fenghao
-- ----------------------------
DROP TABLE IF EXISTS `fenghao`;
CREATE TABLE `fenghao`  (
  `f_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_reason` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`f_id`) USING BTREE,
  INDEX `u_id_fk`(`u_id`) USING BTREE,
  CONSTRAINT `u_id_fk` FOREIGN KEY (`u_id`) REFERENCES `usersb` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fenghao
-- ----------------------------

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `q_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `q_title` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `q_subject` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `q_image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `q_class` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `q_zhangjie` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `q_jiexi` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`q_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('00001', '已注册登记的机动车，改变机动车车身颜色的应到公安交通管理部门申请变更登记', '科目一', '空', '判断题', '无', '这是第1题的解析');
INSERT INTO `question` VALUES ('00002', '对违法驾驶发生重大交通事故且构成犯罪的，不追究其刑任', '科目一', '空', '判断题', '标志题', '这是第2题的解析');
INSERT INTO `question` VALUES ('00003', '驾驶机动车在道路上违反道路交通安全法的行为，属于什么行为？', '科目一', '空', '单项选择题', '手势题', '这是第3题的解析');
INSERT INTO `question` VALUES ('00004', '机动车驾驶人违法驾驶造成重大交通事故构成犯罪的，依法追究什么责任？', '科目一', '空', '单项选择题', '灯光题', '这是第4题的解析');
INSERT INTO `question` VALUES ('00005', '机动车驾驶人造成事故后逃逸构成犯罪的，吊销驾驶证且多长时间不得重新取得驾驶证？', '科目一', '空', '单项选择题', '手势题', '这是第5题的解析');
INSERT INTO `question` VALUES ('00006', ' 下列哪种标志是驾驶机动车上路行驶应当在车上放置的标志？', '科目一', '空', '单项选择题', '标志题', '这是第6题的解析');
INSERT INTO `question` VALUES ('00007', ' 上路行驶的机动车未随车携带身份证的，交通警察可依法扣留机动车。', '科目一', '空', '判断题', '灯光题', '这是第7题的解析');
INSERT INTO `question` VALUES ('00008', '已经达到报废标准的机动车经大修后可以上路行驶。', '科目一', '空', '判断题', '无', '这是第8题的解析');
INSERT INTO `question` VALUES ('00009', '驾驶机动车应当随身携带哪种证件？', '科目一', '空', '单项选择题', '标志题', '这是第9题的解析');
INSERT INTO `question` VALUES ('00010', '未取得驾驶证的学员在道路上学习驾驶技能，下列哪种做法是正确的？', '科目一', '空', '单项选择题', '无', '这是第10题的解析');
INSERT INTO `question` VALUES ('00011', '路面白色反光虚线警告前方路段要减速慢行。', '科目四', '空', '判断题', '标志题', '这是第11题的解析');
INSERT INTO `question` VALUES ('00012', '夜间行车时，全车灯光突然熄灭，应当立即迅速制动，靠边停车。', '科目四', '空', '判断题', '手势题', '这是第12题的解析');
INSERT INTO `question` VALUES ('00013', '在高速公路上遇到紧急情况时不要轻易急转向避让。', '科目四', '空', '判断题', '灯光题', '这是第13题的解析');
INSERT INTO `question` VALUES ('00014', '在泥泞路段遇驱动车轮空转打滑时如何处置？', '科目四', '空', '单项选择题', '标志题', '这是第14题的解析');
INSERT INTO `question` VALUES ('00015', '机动车在雨天临时停车时，应开启什么灯？', '科目四', '空', '单项选择题', '手势题', '这是第15题的解析');
INSERT INTO `question` VALUES ('00016', '机动车在狭窄的坡路会车时，正确的会车方法是什么？', '科目四', '空', '单项选择题', '灯光题', '这是第16题的解析');
INSERT INTO `question` VALUES ('00017', '驾驶机动车在山区道路遇到这种情况怎样行驶？', '科目四', '空', '单项选择题', '无', '这是第17题的解析');
INSERT INTO `question` VALUES ('00018', '抢救骨折伤员时注意什么？', '科目四', '空', '单项选择题', '无', '这是第18题的解析');
INSERT INTO `question` VALUES ('00019', '行车中遇突然爆胎时，驾驶人要急踏制动踏板减速停车。', '科目四', '空', '判断题', '无', '这是第19题的解析');
INSERT INTO `question` VALUES ('00020', '烧伤伤员口渴时，只能喝白开水。', '科目四', '空', '判断题', '无', '这是第20题的解析');
INSERT INTO `question` VALUES ('00021', '这是第一个多项选择题  A  B  C   是答案', '科目四', '空', '多项选择题', '无', '这是第21题的解析');
INSERT INTO `question` VALUES ('00022', '这是第2个多项选择题  A  B  C  D 是答案', '科目四', '空', '多项选择题', '无', '这是第22题的解析');

-- ----------------------------
-- Table structure for qzhengli
-- ----------------------------
DROP TABLE IF EXISTS `qzhengli`;
CREATE TABLE `qzhengli`  (
  `qz_que_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `qz_car_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`qz_que_id`, `qz_car_id`) USING BTREE,
  INDEX `qz_car_id_fk`(`qz_car_id`) USING BTREE,
  CONSTRAINT `qz_car_id_fk` FOREIGN KEY (`qz_car_id`) REFERENCES `car` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `qz_que_id_fk` FOREIGN KEY (`qz_que_id`) REFERENCES `question` (`q_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qzhengli
-- ----------------------------
INSERT INTO `qzhengli` VALUES ('00001', '01');
INSERT INTO `qzhengli` VALUES ('00002', '01');
INSERT INTO `qzhengli` VALUES ('00003', '01');
INSERT INTO `qzhengli` VALUES ('00004', '01');
INSERT INTO `qzhengli` VALUES ('00005', '01');
INSERT INTO `qzhengli` VALUES ('00006', '01');
INSERT INTO `qzhengli` VALUES ('00007', '01');
INSERT INTO `qzhengli` VALUES ('00008', '01');
INSERT INTO `qzhengli` VALUES ('00009', '01');
INSERT INTO `qzhengli` VALUES ('00010', '01');
INSERT INTO `qzhengli` VALUES ('00011', '01');
INSERT INTO `qzhengli` VALUES ('00012', '01');
INSERT INTO `qzhengli` VALUES ('00013', '01');
INSERT INTO `qzhengli` VALUES ('00014', '01');
INSERT INTO `qzhengli` VALUES ('00015', '01');
INSERT INTO `qzhengli` VALUES ('00016', '01');
INSERT INTO `qzhengli` VALUES ('00017', '01');
INSERT INTO `qzhengli` VALUES ('00018', '01');
INSERT INTO `qzhengli` VALUES ('00019', '01');
INSERT INTO `qzhengli` VALUES ('00020', '01');
INSERT INTO `qzhengli` VALUES ('00021', '01');
INSERT INTO `qzhengli` VALUES ('00022', '01');
INSERT INTO `qzhengli` VALUES ('00001', '02');
INSERT INTO `qzhengli` VALUES ('00002', '02');
INSERT INTO `qzhengli` VALUES ('00003', '02');
INSERT INTO `qzhengli` VALUES ('00004', '02');
INSERT INTO `qzhengli` VALUES ('00005', '02');
INSERT INTO `qzhengli` VALUES ('00006', '02');
INSERT INTO `qzhengli` VALUES ('00007', '02');
INSERT INTO `qzhengli` VALUES ('00008', '02');
INSERT INTO `qzhengli` VALUES ('00009', '02');
INSERT INTO `qzhengli` VALUES ('00010', '02');
INSERT INTO `qzhengli` VALUES ('00011', '02');
INSERT INTO `qzhengli` VALUES ('00012', '02');
INSERT INTO `qzhengli` VALUES ('00013', '02');
INSERT INTO `qzhengli` VALUES ('00014', '02');
INSERT INTO `qzhengli` VALUES ('00015', '02');
INSERT INTO `qzhengli` VALUES ('00016', '02');
INSERT INTO `qzhengli` VALUES ('00017', '02');
INSERT INTO `qzhengli` VALUES ('00018', '02');
INSERT INTO `qzhengli` VALUES ('00019', '02');
INSERT INTO `qzhengli` VALUES ('00020', '02');
INSERT INTO `qzhengli` VALUES ('00021', '02');
INSERT INTO `qzhengli` VALUES ('00022', '02');
INSERT INTO `qzhengli` VALUES ('00001', '03');
INSERT INTO `qzhengli` VALUES ('00002', '03');
INSERT INTO `qzhengli` VALUES ('00003', '03');
INSERT INTO `qzhengli` VALUES ('00004', '03');
INSERT INTO `qzhengli` VALUES ('00005', '03');
INSERT INTO `qzhengli` VALUES ('00006', '03');
INSERT INTO `qzhengli` VALUES ('00007', '03');
INSERT INTO `qzhengli` VALUES ('00008', '03');
INSERT INTO `qzhengli` VALUES ('00009', '03');
INSERT INTO `qzhengli` VALUES ('00010', '03');
INSERT INTO `qzhengli` VALUES ('00011', '03');
INSERT INTO `qzhengli` VALUES ('00012', '03');
INSERT INTO `qzhengli` VALUES ('00013', '03');
INSERT INTO `qzhengli` VALUES ('00014', '03');
INSERT INTO `qzhengli` VALUES ('00015', '03');
INSERT INTO `qzhengli` VALUES ('00016', '03');
INSERT INTO `qzhengli` VALUES ('00017', '03');
INSERT INTO `qzhengli` VALUES ('00018', '03');
INSERT INTO `qzhengli` VALUES ('00019', '03');
INSERT INTO `qzhengli` VALUES ('00020', '03');
INSERT INTO `qzhengli` VALUES ('00021', '03');
INSERT INTO `qzhengli` VALUES ('00022', '03');
INSERT INTO `qzhengli` VALUES ('00001', '04');
INSERT INTO `qzhengli` VALUES ('00002', '04');
INSERT INTO `qzhengli` VALUES ('00003', '04');
INSERT INTO `qzhengli` VALUES ('00004', '04');
INSERT INTO `qzhengli` VALUES ('00005', '04');
INSERT INTO `qzhengli` VALUES ('00006', '04');
INSERT INTO `qzhengli` VALUES ('00007', '04');
INSERT INTO `qzhengli` VALUES ('00008', '04');
INSERT INTO `qzhengli` VALUES ('00009', '04');
INSERT INTO `qzhengli` VALUES ('00010', '04');
INSERT INTO `qzhengli` VALUES ('00011', '04');
INSERT INTO `qzhengli` VALUES ('00012', '04');
INSERT INTO `qzhengli` VALUES ('00013', '04');
INSERT INTO `qzhengli` VALUES ('00014', '04');
INSERT INTO `qzhengli` VALUES ('00015', '04');
INSERT INTO `qzhengli` VALUES ('00016', '04');
INSERT INTO `qzhengli` VALUES ('00017', '04');
INSERT INTO `qzhengli` VALUES ('00018', '04');
INSERT INTO `qzhengli` VALUES ('00019', '04');
INSERT INTO `qzhengli` VALUES ('00020', '04');
INSERT INTO `qzhengli` VALUES ('00021', '04');
INSERT INTO `qzhengli` VALUES ('00022', '04');
INSERT INTO `qzhengli` VALUES ('00001', '05');
INSERT INTO `qzhengli` VALUES ('00002', '05');
INSERT INTO `qzhengli` VALUES ('00006', '05');
INSERT INTO `qzhengli` VALUES ('00010', '05');
INSERT INTO `qzhengli` VALUES ('00005', '06');
INSERT INTO `qzhengli` VALUES ('00006', '06');
INSERT INTO `qzhengli` VALUES ('00007', '06');
INSERT INTO `qzhengli` VALUES ('00008', '06');
INSERT INTO `qzhengli` VALUES ('00005', '07');
INSERT INTO `qzhengli` VALUES ('00006', '07');
INSERT INTO `qzhengli` VALUES ('00007', '07');
INSERT INTO `qzhengli` VALUES ('00008', '07');
INSERT INTO `qzhengli` VALUES ('00001', '08');
INSERT INTO `qzhengli` VALUES ('00002', '08');
INSERT INTO `qzhengli` VALUES ('00006', '08');
INSERT INTO `qzhengli` VALUES ('00010', '08');

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
  `r_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `r_tm_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `r_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `r_neirong` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `r_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`r_id`) USING BTREE,
  INDEX `r_user_id_fk`(`r_user_id`) USING BTREE,
  INDEX `r_tm_id_fk`(`r_tm_id`) USING BTREE,
  CONSTRAINT `r_tm_id_fk` FOREIGN KEY (`r_tm_id`) REFERENCES `tiemain` (`tm_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `r_user_id_fk` FOREIGN KEY (`r_user_id`) REFERENCES `usersb` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('00006', '00003', '00003', '题目里有\"口\"的选50米, 有\"站\"的选30米, 叫\"口五站三\" 剩下的全选150米, 没有150的选最大.判断题全对. ', '2016-01-01');
INSERT INTO `reply` VALUES ('00007', '00003', '00003', '车在<公交站/急救站/加油站/消防队、站、栓> (30米)以内,不得停车. ', '2016-01-03');
INSERT INTO `reply` VALUES ('00008', '00003', '00003', '车在<距交叉路口/隧道口/陡坡/弯路/窄路>(50米)以内不得停车', '2016-01-03');
INSERT INTO `reply` VALUES ('00009', '00003', '00003', '有关公里的题目:城市街道选50公里,其余有30的全选30.', '2016-01-03');
INSERT INTO `reply` VALUES ('00010', '00003', '00003', '有\"不得停车\"的选择\"不得停车\" ', '2016-01-03');
INSERT INTO `reply` VALUES ('00011', '00004', '00003', '上车调好后视镜，这很重要', '2016-01-03');
INSERT INTO `reply` VALUES ('00012', '00004', '00001', '把车开到指定区域，然后倒车，注意观察右后视镜，看库角向镜框底部移动', '2016-01-03');
INSERT INTO `reply` VALUES ('00013', '00004', '00002', '眼镜盯牢库角的移动，等其消失，意味着后轮进入库区，这是一个打轮的时机，向右打死方向', '2016-01-03');
INSERT INTO `reply` VALUES ('00014', '00004', '00003', '这时看左后视镜，随着车子右倒，车尾渐渐进入库内，黄色虚线渐渐靠近中间的车把，这是一个重要的点位，也是回正方向的时机点，沿着车身看到后库角到点就回正方向', '2016-01-03');
INSERT INTO `reply` VALUES ('00015', '00005', '00001', '恭喜', '2016-01-03');
INSERT INTO `reply` VALUES ('00016', '00006', '00001', '应该是扣10分', '2016-01-03');
INSERT INTO `reply` VALUES ('00017', '00006', '00002', '扣10分，熄火和溜车不及格', '2016-01-03');
INSERT INTO `reply` VALUES ('00018', '00007', '00002', '两次', '2016-01-03');
INSERT INTO `reply` VALUES ('00019', '00008', '00001', '可以微调，但不能有明显转动', '2016-01-03');
INSERT INTO `reply` VALUES ('00020', '00008', '00002', '要是觉得会压线，就一个手稍微用点力进行微调就行，但不能有明显动作', '2016-01-03');
INSERT INTO `reply` VALUES ('00021', '00009', '00003', '路考的时候，教练会带着你跟着考试车辆后面，前面一个考完下车后，拿好身份证从考试车辆的右边走上去，从车头绕过去，从车的左边走到车后面检查路面状况和检查后面是否有人，顺便确认车停的位置是否斜坡。', '2016-01-03');
INSERT INTO `reply` VALUES ('00022', '00009', '00003', '检查完成后走回去轻轻打开车门，并且很有礼貌的向考官问好。', '2016-01-03');
INSERT INTO `reply` VALUES ('00023', '00009', '00003', '上车后把门关实（关到约20厘米时稍大力一点，保证车门关好），上车后双手拿身份证给考官查看（身份证正面朝考官，让考官一拿到身份证就要以看到资料，不用再把身份证倒过来）并且有礼貌的说：“考官，您好！这是我的身份证”。', '2016-01-03');
INSERT INTO `reply` VALUES ('00024', '00009', '00003', '下车前，是否合格都有礼貌的对考官说声：“谢谢！”。先看左后视镜，确认安全后再打开车门，下车后记得关好车门，不要太大力了，如果关门太猛，吓到考官，分分钟把你叫回来判个不合格！', '2016-01-03');
INSERT INTO `reply` VALUES ('00025', '00009', '00003', '上车后，调整座位，系好安全带，双手扶着方向盘，检查左、中、右后视镜是否需要调整。伸出左手，开启大灯后放回方向盘，两三秒后再关掉大灯，手放回方向盘。路考分日考和夜考，夜考就要考你是否会打开车头大灯。是否需要开灯就要看您当地的考试要求了', '2016-01-03');
INSERT INTO `reply` VALUES ('00026', '00009', '00003', '做好起步准备，等考官叫你开始起步后，先看右、中、左后视镜（头部动作稍为大一点，每次约1-2秒，不能长时间盯着后视镜，特别是车辆行驶过程），起步前要打左转向灯，看左后视镜，按一下喇叭，先挂一档再放手刹（如果车辆停在斜坡位置，就要用斜坡起步的方法，先松一点离合，让车有一点抖动再放手刹起步），慢慢的松离合，不要让车抖动得太厉害。如果起步熄火了，必须拉上手刹挂回空档再打火，否则第二次机会你也会失去。', '2016-01-03');
INSERT INTO `reply` VALUES ('00027', '00009', '00003', '遇到斑马线，需要减速，如果斑马线上有行人，不管是否绿灯都需要停车先让行人通过。千万别按喇叭催行人，因为机动车必须让行。', '2016-01-03');
INSERT INTO `reply` VALUES ('00028', '00010', '00003', '就是判断单选多选不过有的是看动画或者图片答题', '2016-01-03');
INSERT INTO `reply` VALUES ('00029', '00010', '00002', '判断，单选，多选', '2016-01-03');
INSERT INTO `reply` VALUES ('00030', '00011', '00003', '考试及格，在宣誓大厅宣誓后就能拿到驾驶本', '2016-01-03');
INSERT INTO `reply` VALUES ('00031', '00011', '00001', '考完大概得等半个小时就应该拿到了，在宣誓大厅', '2016-01-03');
INSERT INTO `reply` VALUES ('00032', '00012', '00001', '考完后10天', '2016-01-03');
INSERT INTO `reply` VALUES ('00033', '00012', '00002', '考完后10天可以再次申请', '2016-01-03');
INSERT INTO `reply` VALUES ('00034', '00013', '00004', '这是闲聊', '2016-01-03');
INSERT INTO `reply` VALUES ('00035', '00013', '00002', '这是闲聊', '2016-01-03');
INSERT INTO `reply` VALUES ('00036', '00014', '00003', '这是闲聊', '2016-01-03');
INSERT INTO `reply` VALUES ('00037', '00014', '00002', '这是闲聊', '2016-01-03');
INSERT INTO `reply` VALUES ('00038', '00015', '00001', '这是闲聊', '2016-01-03');
INSERT INTO `reply` VALUES ('00039', '00015', '00004', '这是闲聊', '2016-01-03');

-- ----------------------------
-- Table structure for tieclass
-- ----------------------------
DROP TABLE IF EXISTS `tieclass`;
CREATE TABLE `tieclass`  (
  `tc_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tc_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tc_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tieclass
-- ----------------------------
INSERT INTO `tieclass` VALUES ('0001', '科一');
INSERT INTO `tieclass` VALUES ('0002', '科二');
INSERT INTO `tieclass` VALUES ('0003', '科三');
INSERT INTO `tieclass` VALUES ('0004', '科四');
INSERT INTO `tieclass` VALUES ('0005', '闲聊');

-- ----------------------------
-- Table structure for tiemain
-- ----------------------------
DROP TABLE IF EXISTS `tiemain`;
CREATE TABLE `tiemain`  (
  `tm_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tm_class_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tm_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tm_title` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tm_neirong` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tm_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`tm_id`) USING BTREE,
  INDEX `tm_user_id_fk`(`tm_user_id`) USING BTREE,
  INDEX `tm_calss_id_fk`(`tm_class_id`) USING BTREE,
  CONSTRAINT `tm_calss_id_fk` FOREIGN KEY (`tm_class_id`) REFERENCES `tieclass` (`tc_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tm_user_id_fk` FOREIGN KEY (`tm_user_id`) REFERENCES `usersb` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tiemain
-- ----------------------------
INSERT INTO `tiemain` VALUES ('00003', '0001', '00003', '题目00003', '科一部分题速记方法', '2016-01-13');
INSERT INTO `tiemain` VALUES ('00004', '0002', '00004', '题目00004', '侧方停车需要注意的几点', '2016-01-13');
INSERT INTO `tiemain` VALUES ('00005', '0002', '00002', '题目00005', '科二已过，90分', '2016-01-01');
INSERT INTO `tiemain` VALUES ('00006', '0002', '00001', '题目00006', '坡道定点停车未在指定地点停算不及格？', '2015-11-14');
INSERT INTO `tiemain` VALUES ('00007', '0003', '00002', '题目00007', '大路考有几次机会', '2016-01-13');
INSERT INTO `tiemain` VALUES ('00008', '0003', '00003', '题目00008', '直线行驶方向盘一点都不能动吗？', '2015-10-23');
INSERT INTO `tiemain` VALUES ('00009', '0003', '00001', '题目00009', '科三注意事项', '2015-07-22');
INSERT INTO `tiemain` VALUES ('00010', '0004', '00001', '题目00010', '科四有几种试题类型', '2015-04-02');
INSERT INTO `tiemain` VALUES ('00011', '0004', '00002', '题目00011', '科四考完就能拿到本？', '2015-03-25');
INSERT INTO `tiemain` VALUES ('00012', '0004', '00004', '题目00012', '科四没过，多长时间能补考？', '2015-09-16');
INSERT INTO `tiemain` VALUES ('00013', '0005', '00001', '题目00013', '这个是我添加的闲聊第一个', '2015-04-02');
INSERT INTO `tiemain` VALUES ('00014', '0005', '00002', '题目00014', '这个也是我添加的闲聊第二个？', '2015-03-25');
INSERT INTO `tiemain` VALUES ('00015', '0005', '00004', '题目00015', '这个也是我添加的？', '2015-09-16');

-- ----------------------------
-- Table structure for usersb
-- ----------------------------
DROP TABLE IF EXISTS `usersb`;
CREATE TABLE `usersb`  (
  `u_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_zhanghao` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_ca_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_ds_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_nicheng` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_touxiang` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_age` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_denglu` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_anquanma` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE,
  INDEX `u_ca_id_fk`(`u_ca_id`) USING BTREE,
  INDEX `u_ds_id_fk`(`u_ds_id`) USING BTREE,
  CONSTRAINT `u_ca_id_fk` FOREIGN KEY (`u_ca_id`) REFERENCES `car` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `u_ds_id_fk` FOREIGN KEY (`u_ds_id`) REFERENCES `drivesc` (`ds_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of usersb
-- ----------------------------
INSERT INTO `usersb` VALUES ('00001', 'u1', '01', '001', 'u1', '昵称u1', '空', '55', '男', '1234567', '唐山', '是', 'bbb');
INSERT INTO `usersb` VALUES ('00002', 'u2', '02', '002', 'u2', '昵称u2', '空', '22', '女', '1234567', '天津', '是', 'aaa');
INSERT INTO `usersb` VALUES ('00003', 'a3', '03', '003', 'a3', '昵称a3', '空', '32', '男', '1234567', '秦皇岛', '是', 'ccc');
INSERT INTO `usersb` VALUES ('00004', 'a4', '04', '004', 'a4', '昵称a4', '空', '32', '男', '1234567', '乌鲁木齐', '是', 'mmm');

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `v_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `v_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `v_lujing` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `v_image` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `v_car_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`v_id`) USING BTREE,
  INDEX `v_car_id_fk`(`v_car_id`) USING BTREE,
  CONSTRAINT `v_car_id_fk` FOREIGN KEY (`v_car_id`) REFERENCES `car` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES ('00001', '倒车入库', 'http://v.youku.com/v_show/id_XMTMxODQxNjkwNA==.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00002', '坡道顶点停车和起步', 'https://www.baidu.com/', '123.png', '01');
INSERT INTO `video` VALUES ('00003', '侧方停车', 'http://v.youku.com/v_show/id_XODcyNjA1NjMy.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00004', 'S路行驶', 'http://v.youku.com/v_show/id_XMTI1NDg3NTA4NA==.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00005', '直角转弯', 'http://v.youku.com/v_show/id_XMTI1NDg3NTA4NA==.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00006', '上车准备', 'http://v.youku.com/v_show/id_XNjIwMDk0MDU2.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00007', '模拟夜间场景灯光使用', 'http://v.youku.com/v_show/id_XNzYxMzQ5Njg0.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00008', '起步', 'http://v.youku.com/v_show/id_XMTQzMjkyMDU1Ng==.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00009', '通过人行横道', 'http://v.youku.com/v_show/id_XOTEwNjE3MDA0.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00010', '超车', 'http://v.youku.com/v_show/id_XNDQ5MzE0ODEy.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00011', '变更车道', 'http://v.youku.com/v_show/id_XNDQ5MzE0ODEy.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00012', '直线行驶', 'http://v.youku.com/v_show/id_XMTMzMDgyNDEwNA==.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00013', '通过学校区域', 'http://v.youku.com/v_show/id_XOTEwNjE3MDA0.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00014', '会车', 'http://v.youku.com/v_show/id_XOTEwNjE3MDA0.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00015', '通过公共汽车站', 'http://v.youku.com/v_show/id_XOTEwNjE3MDA0.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00016', '加减档操作', 'http://v.youku.com/v_show/id_XMTMzMDgyNDEwNA==.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00017', '窄路掉头', 'http://v.youku.com/v_show/id_XMTI4MTI3ODgyNA==.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00018', '靠边停车', 'http://v.youku.com/v_show/id_XOTE0NzQ2MzM2.html?from=s1.8-1-1.2', '123.png', '01');
INSERT INTO `video` VALUES ('00019', '夜间行驶', 'http://v.youku.com/v_show/id_XODUyMzQ3ODcy.html?from=s1.8-1-1.2', '123.png', '01');

SET FOREIGN_KEY_CHECKS = 1;
