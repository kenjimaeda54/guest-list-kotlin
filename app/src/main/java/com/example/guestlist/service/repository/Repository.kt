package com.example.guestlist.service.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.convidados.service.constants.DataBaseConstants
import com.example.guestlist.service.model.GuestModel
import java.lang.Exception

class Repository private constructor(context: Context) {

    private var getDatabaseHelper: GetDataBaseHelper = GetDataBaseHelper(context)
    //vamos aplicar o antipattern singleton,quando desejamos concentrar todos nossos logs da aplicao em apenas uma classe
    //criamos esse anti padrão,assim teremos apenas uma instância da classe. E util por exemplo em banco de dados
    //voce fecha seu construtor e cria uma membro estático


    companion object {
        private lateinit var mRepository: Repository
        fun getInstance(context: Context): Repository {
            //por ter declaro minha variável no topo preciso de primeiro criou contexto,entao trato dentro do if
            if (!::mRepository.isInitialized) {
                mRepository = Repository(context)
            }
            return mRepository
        }
    }


    @SuppressLint("Range")
    fun getUser(id: Int): GuestModel? {

        var guest: GuestModel? = null

        return try {
            val db = getDatabaseHelper.readableDatabase

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())
            val columns = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.PRESENCE,
                DataBaseConstants.GUEST.COLUMNS.NAME
            )
            //os outros 3 métodos sao para consulta no banco de dados, groupBy,having,orderBY
            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                columns,
                selection,
                args,
                null,
                null,
                null
            )

            //cursor e um propriedade do proprio banco,se for mairo que 0 e porque retornou algo
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                val name =
                    cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                //presence e boolean,mas o banco de dados nao trata booleano, e 0 ou 1
                val presence =
                    (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)
                //se for igual 1 retorna true
                guest = GuestModel(id, name, presence)
            }
            cursor.close();
            guest

        } catch (e: Exception) {

            guest
        }


    }


    fun salve(guest: GuestModel): Boolean {
        return try {
            val db = getDatabaseHelper.writableDatabase
            val constanteValues = ContentValues()
            //id vai ser tratado pelo banco de dados
            constanteValues.put(
                DataBaseConstants.GUEST.COLUMNS.NAME,
                guest.name
            )
            constanteValues.put(
                DataBaseConstants.GUEST.COLUMNS.PRESENCE,
                guest.presence
            )
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, constanteValues)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = getDatabaseHelper.writableDatabase
            val constanteValues = ContentValues()
            constanteValues.put(
                DataBaseConstants.GUEST.COLUMNS.NAME,
                guest.name
            )
            constanteValues.put(
                DataBaseConstants.GUEST.COLUMNS.PRESENCE,
                guest.presence
            )
            val clause = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())
            db.update(DataBaseConstants.GUEST.TABLE_NAME, constanteValues, clause, args)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun delete(id: Int): Boolean {
        return try {
            val db = getDatabaseHelper.writableDatabase
            val clause = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())
            db.delete(DataBaseConstants.GUEST.TABLE_NAME, clause, args)
            true
        } catch (e: Exception) {
            false
        }
    }


    @SuppressLint("Range")
    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            val db = getDatabaseHelper.readableDatabase

            val columns = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE,
                DataBaseConstants.GUEST.COLUMNS.NAME
            )
            //os outros 3 métodos sao para consulta no banco de dados, groupBy,having,orderBY
            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
            )

            //cursor e um propriedade do proprio banco,se for mairo que 0 e porque retornou algo
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    //presence e boolean,mas o banco de dados nao trata booleano, e 0 ou 1
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)
                    //se for igual 1 retorna true
                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }

            }
            cursor.close();
            list

        } catch (e: Exception) {

            list
        }
    }

    @SuppressLint("Range")
    fun getPresents(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            val db = getDatabaseHelper.readableDatabase

            //rawQuery nao e query mais segura para consultar banco
            //guest e o nome da tabela esta em databaseConstants
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence=1", null)

            //cursor e um propriedade do próprio banco,se for maior que 0 e porque retornou algo
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    //presence e boolean,mas o banco de dados nao trata booleano, e 0 ou 1
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)
                    //se for igual 1 retorna true
                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }

            }
            cursor.close();
            list

        } catch (e: Exception) {

            list
        }

    }

    @SuppressLint("Range")
    fun getAbsents(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            val db = getDatabaseHelper.readableDatabase

            val selection = DataBaseConstants.GUEST.COLUMNS.PRESENCE + "= 1"
            //rawQuery nao e query mais segura para consultar banco
            //guest e o nome da tabela esta em databaseConstants
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence=0", null)

            //cursor e um propriedade do próprio banco,se for maior que 0 e porque retornou algo
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    //presence e boolean,mas o banco de dados nao trata booleano, e 0 ou 1
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)
                    //se for igual 1 retorna true
                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }

            }
            cursor.close();
            list

        } catch (e: Exception) {

            list
        }
    }


}