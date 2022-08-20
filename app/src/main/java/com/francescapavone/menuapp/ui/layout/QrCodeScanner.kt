package com.francescapavone.menuapp.ui.layout

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import java.io.File
import com.francescapavone.menuapp.ui.utils.QRCodeAnalyzer


@Composable
fun QrCodeScanner() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture =
        remember { ProcessCameraProvider.getInstance(context) }

    var code by remember { mutableStateOf("") }
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCameraPermission = granted
        }
    )

    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (hasCameraPermission) {
            AndroidView(
                factory = { context ->
                    val previewView = PreviewView(context)
                    val preview = Preview.Builder().build()
                    val selector = CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()
                    preview.setSurfaceProvider(previewView.surfaceProvider)
                    val imageAnalysis = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                    imageAnalysis.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        QRCodeAnalyzer { result ->
                            result?.let { code = it }
                        }
                    )

                    try {
                        cameraProviderFuture.get().bindToLifecycle(
                            lifecycleOwner,
                            selector,
                            preview,
                            imageAnalysis
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    return@AndroidView previewView
                },
                modifier = Modifier.weight(1f)
            )
            if (code != "") {
                if (code.startsWith("https://github.com/al3ssandrocaruso/restaurantsappdata/raw/main/menus/PDFsMenu/")) {
                    Dialog(onDismissRequest = { code = "" }) {
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = Color.White
                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                Column(modifier = Modifier.padding(20.dp)) {

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Please confirm",
                                            style = TextStyle(
                                                fontSize = 24.sp,
                                                fontFamily = FontFamily.Default,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                        Icon(
                                            imageVector = Icons.Filled.Cancel,
                                            contentDescription = "",
                                            tint = colorResource(android.R.color.darker_gray),
                                            modifier = Modifier
                                                .width(30.dp)
                                                .height(30.dp)
                                                .clickable { code = "" }
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(20.dp))

                                    Spacer(modifier = Modifier.height(20.dp))

                                    Box(
                                        modifier = Modifier.padding(
                                            40.dp,
                                            0.dp,
                                            40.dp,
                                            0.dp
                                        )
                                    ) {
                                        Button(
                                            onClick = {
//                                                download(code, "menu", context)
                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                backgroundColor = Color(0xFF2E7558)
                                            ),
                                            shape = RoundedCornerShape(50.dp),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(50.dp)
                                        ) {
                                            Text(
                                                text = "Menu Download",
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFfeca38)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Dialog(onDismissRequest = { code = "" }) {
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = Color.White
                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                Column(modifier = Modifier.padding(20.dp)) {

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "QR not valid",
                                            style = TextStyle(
                                                fontSize = 24.sp,
                                                fontFamily = FontFamily.Default,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                        Icon(
                                            imageVector = Icons.Filled.Cancel,
                                            contentDescription = "",
                                            tint = colorResource(android.R.color.darker_gray),
                                            modifier = Modifier
                                                .width(30.dp)
                                                .height(30.dp)
                                                .clickable { code = "" }
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(20.dp))

                                    Spacer(modifier = Modifier.height(20.dp))

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
/*private fun download(url: String, fileName: String, context: Context) {
    val dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    val fileLink = Uri.parse(url)
    val request = DownloadManager.Request(fileLink)

    try {
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
            .setMimeType("application/pdf")
            .setAllowedOverRoaming(false)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("$fileName.pdf")
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                File.separator + fileName + ".pdf")

        dm.enqueue(request)
        Toast.makeText(context, "PDF is Downloaded", Toast.LENGTH_LONG).show()

    } catch (e: Exception) {
        Toast.makeText(context, "Download Failed", Toast.LENGTH_LONG).show()
    }
}*/


