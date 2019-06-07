package com.imooc.icake.dao;

import com.imooc.icake.entity.Cake;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CakeDao {
    @Insert("insert into cake(title,cid,image_path,price,taste,sweetness,weight,size,material,status) values(#{title},#{cid},#{imagePath},#{price},#{taste},#{sweetness},#{weight},#{size},#{material},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Cake cake);

    @Delete("delete from cake where id=#{id}")
    void delete(int id);

    @Update("update cake set title=#{title},cid=#{cid},image_path=#{imagePath},price=#{price},taste=#{taste},sweetness=#{sweetness},weight=#{weight},size=#{size},material=#{material},status=#{status} where id=#{id}")
    void update(Cake cake);

    @Select("select c.*,ca.title ctitle from cake c left join catalog ca on c.cid=ca.id where c.id=#{id}")
    @Results(id="all",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "cid",property = "cid"),
            @Result(column = "image_path",property = "imagePath"),
            @Result(column = "price",property = "price"),
            @Result(column = "taste",property = "taste"),
            @Result(column = "sweetness",property = "sweetness"),
            @Result(column = "weight",property = "weight"),
            @Result(column = "size",property = "size"),
            @Result(column = "material",property = "material"),
            @Result(column = "status",property = "status"),
            @Result(column = "ctitle",property = "catalog.title")
    })
    Cake select(int id);

    @Select("select c.*,ca.title catitle from cake c left join catalog ca on c.id=ca.id order by id desc")
    @ResultMap("all")
    List<Cake> selectAll();
}
