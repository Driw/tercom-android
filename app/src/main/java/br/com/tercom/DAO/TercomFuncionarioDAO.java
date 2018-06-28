package br.com.tercom.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import br.com.tercom.Entity.TercomFuncionario;

@Dao
public interface TercomFuncionarioDAO {
    //TODO("Corrigir Query")
    @Query("SELECT * FROM tercom_funcionario WHERE tercom_funcionario.id = :id")
    public TercomFuncionario getFuncionario(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(TercomFuncionario func);

    @Update
    public int update(TercomFuncionario func);

    @Delete
    public int delete(TercomFuncionario func);
}
