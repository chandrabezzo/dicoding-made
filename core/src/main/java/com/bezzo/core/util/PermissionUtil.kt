package com.bezzo.core.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bezzo.core.R


/**
 * Created by Lafran on 10/30/17.
 */
object PermissionUtil {
    const val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 122
    const val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123
    const val MY_PERMISSIONS_REQUEST_READ_DEVICE_ID = 124
    const val MY_PERMISSIONS_REQUEST_VIBRATE = 125
    const val MY_PERMISSIONS_REQUEST_ACCESS_NETWORK_STATE = 126
    const val MY_PERMISSIONS_CAMERA = 127
    const val MY_PERMISSIONS_AUDIO = 128
    const val MY_PERMISSIONS_FINE_LOCATION = 129
    const val MY_PERMISSIONS_COARSE_LOCATION = 129
    const val MY_PERMISSIONS_READ_SMS = 130
    const val MY_PERMISSIONS_STORAGE = 131

    fun requestStoragePermisison(context: Context): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        val readPermission = Manifest.permission.READ_EXTERNAL_STORAGE
        val writePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE

        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (isNotGranted(context, writePermission) && isNotGranted(context, readPermission)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, writePermission)
                        && ActivityCompat.shouldShowRequestPermissionRationale(context, readPermission)) {
                    showAlertDialog(context, context.getString(R.string.title_permission),
                            String.format(context.getString(R.string.content_permission),
                                          context.getString(R.string.penyimpanan)),
                            arrayOf(writePermission, readPermission), MY_PERMISSIONS_STORAGE)
                } else {
                    requestPermission(context, arrayOf(writePermission, readPermission), MY_PERMISSIONS_STORAGE)
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }

    fun requestReadExternalPermission(context: Context): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (isNotGranted(context, permission)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)) {
                    showAlertDialog(context, context.getString(R.string.title_permission),
                            "Ijinkan aplikasi untuk mengakses penyimpanan eksternal?",
                            arrayOf(permission), MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
                } else {
                    requestPermission(context, arrayOf(permission), MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }

    fun requestReadSMSPermission(context: Context): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        val permission = Manifest.permission.READ_SMS
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (isNotGranted(context, permission)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)) {
                    showAlertDialog(context, context.getString(R.string.title_permission),
                            String.format(context.getString(R.string.message_perizinan), "SMS"),
                            arrayOf(permission), MY_PERMISSIONS_READ_SMS)
                } else {
                    requestPermission(context, arrayOf(permission), MY_PERMISSIONS_READ_SMS)
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }

    fun requestWriteExternalPermission(context: Context): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (isNotGranted(context, permission)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)) {
                    showAlertDialog(context, context.getString(R.string.title_permission),
                            "Ijinkan aplikasi untuk menyimpan ke data eksternal?",
                            arrayOf(permission), MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
                } else {
                    requestPermission(context, arrayOf(permission), MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }

    fun requestCameraPermission(context: Context): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        val permission = android.Manifest.permission.CAMERA
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (isNotGranted(context, permission)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)) {
                    showAlertDialog(context, "Ijinkan Aplikasi?", "Ijinkan aplikasi untuk mengakses kamera?",
                            arrayOf(permission), MY_PERMISSIONS_CAMERA)
                } else {
                    requestPermission(context, arrayOf(permission), MY_PERMISSIONS_CAMERA)
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }

    fun requestAudioPermission(context: Context): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        val permission = Manifest.permission.RECORD_AUDIO
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (isNotGranted(context, permission)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)) {
                    showAlertDialog(context, "Ijinkan Aplikasi?", "Ijinkan aplikasi untuk mengakses mikrofon?",
                            arrayOf(permission), MY_PERMISSIONS_AUDIO)
                } else {
                    requestPermission(context, arrayOf(permission), MY_PERMISSIONS_AUDIO)
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }

    fun requestFineLocationPermission(context: Context): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (isNotGranted(context, permission)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)) {
                    showAlertDialog(context, "Ijinkan Aplikasi?", "Ijinkan aplikasi untuk mengakses lokasi?",
                            arrayOf(permission), MY_PERMISSIONS_FINE_LOCATION)
                } else {
                    requestPermission(context, arrayOf(permission), MY_PERMISSIONS_FINE_LOCATION)
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }

    fun requestCoarseLocationPermission(context: Context): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        val permission = Manifest.permission.ACCESS_COARSE_LOCATION
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (isNotGranted(context, permission)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)) {
                    showAlertDialog(context, "Ijinkan Aplikasi?", "Ijinkan aplikasi untuk mengakses lokasi?",
                            arrayOf(permission), MY_PERMISSIONS_COARSE_LOCATION)
                } else {
                    requestPermission(context, arrayOf(permission), MY_PERMISSIONS_COARSE_LOCATION)
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }

    fun requestDeviceIdPermission(context: Context): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        val permission = Manifest.permission.READ_PHONE_STATE
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (isNotGranted(context, permission)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)) {
                    showAlertDialog(context, "Ijinkan Aplikasi?", "Aplikasi membutuhkan perangkat id Anda, ijinkan?",
                            arrayOf(permission), MY_PERMISSIONS_REQUEST_READ_DEVICE_ID)
                } else {
                    requestPermission(context, arrayOf(permission), MY_PERMISSIONS_REQUEST_READ_DEVICE_ID)
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }

    fun requestVibrate(context: Context): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        val permission = Manifest.permission.VIBRATE
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (isNotGranted(context, permission)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)) {
                    showAlertDialog(context, "Aktifkan Fitur Vibrasi?",
                            "Ijinkan aplikasi untuk mengelola fitur vibrasi pada perangkat Anda?",
                            arrayOf(permission), MY_PERMISSIONS_REQUEST_VIBRATE)
                } else {
                    requestPermission(context, arrayOf(permission), MY_PERMISSIONS_REQUEST_VIBRATE)
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }

    fun requestAccessNetworkState(context: Context): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        val permission = Manifest.permission.ACCESS_NETWORK_STATE
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (isNotGranted(context, permission)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)) {
                    showAlertDialog(context, "Ijinkan Aplikasi?", "Ijinkan Aplikasi untuk membaca koneksi jaringan Anda?",
                            arrayOf(permission), MY_PERMISSIONS_REQUEST_ACCESS_NETWORK_STATE)
                } else {
                    requestPermission(context, arrayOf(permission), MY_PERMISSIONS_REQUEST_ACCESS_NETWORK_STATE)
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }

    fun showAlertDialog(context: Context, title: String, message: String, permissions: Array<String>, flag: Int) {
        val alertBuilder = AlertDialog.Builder(context)
        alertBuilder.setCancelable(true)
        alertBuilder.setTitle(title)
        alertBuilder.setMessage(message)
        alertBuilder.setPositiveButton(android.R.string.yes) { dialog, which ->
            ActivityCompat.requestPermissions(context as Activity, permissions, flag)
        }
        val alert = alertBuilder.create()
        alert.show()
    }

    private fun isNotGranted(context: Context, manifestPermission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, manifestPermission) != PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(context: Activity, permissions: Array<String>, flag: Int) {
        ActivityCompat.requestPermissions(context, permissions, flag)
    }
}