package com.vn.tcshop.baigiuaki

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var db: DbHelper? = null

    private var add: Button? = null
    private var rg: Register? = null
    private var edtemail: EditText? = null
    private var edtname: EditText? = null
    private var edtpass: EditText? = null
    private var edtconfigpass: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtemail = findViewById(R.id.edtEmail)
        edtname = findViewById(R.id.edtName)
        edtpass = findViewById(R.id.edtPass)
        edtconfigpass = findViewById(R.id.edtConfirmPass)
        db = DbHelper(this)
        // thêm
        add = findViewById(R.id.btnRegister)
        add?.setOnClickListener(View.OnClickListener { addSv() })
    }

    private fun addSv() {
        val email = edtemail!!.text.toString().trim { it <= ' ' }
        val name = edtname!!.text.toString().trim { it <= ' ' }
        val pass = edtpass!!.text.toString().trim { it <= ' ' }
        val confipass = edtconfigpass!!.text.toString().trim { it <= ' ' }
        // Kiểm tra xem các trường đã được nhập đầy đủ hay chưa
        if (TextUtils.isEmpty(email)||TextUtils.isEmpty(name)||TextUtils.isEmpty(pass) || TextUtils.isEmpty(confipass)) {
            // Nếu chưa nhập đầy đủ thì hiển thị thông báo lỗi
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Kiểm tra xem mật khẩu và xác nhận mật khẩu có giống nhau hay không
        if (pass != confipass) {
            // Nếu không giống nhau thì hiển thị thông báo lỗi
            Toast.makeText(this, "Password and confirm password do not match", Toast.LENGTH_SHORT).show()
            return
        }
        rg = Register(-1, email, name, pass)
        db!!.addSv(rg!!)
        Toast.makeText(application, "Add success", Toast.LENGTH_SHORT).show()
    }
}