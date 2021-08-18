package com.saj.marvel.data

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseAuthStateStorage @Inject constructor() : AuthStateStorageInt {
    override fun isUserLoggedIn() = FirebaseAuth.getInstance().currentUser != null
}