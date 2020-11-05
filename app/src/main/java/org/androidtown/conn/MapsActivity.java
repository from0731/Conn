package org.androidtown.conn;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int REQUEST_CODE_ANOTHER = 1001;
    private GoogleMap mMap;
    private Geocoder geocoder;
    private Button button, Button2;
    private EditText editText;

    TextView textView1, textView2, textView3;

    MarkerOptions mOptions2 = new MarkerOptions();
    String address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlemap);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        Button2 = (Button) findViewById(R.id.button2);
        textView1 = (TextView) findViewById(R.id.textView1);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        geocoder = new Geocoder(this);
        googleMap.setOnMarkerClickListener(markerClickListener);

        // 맵 터치 이벤트 구현 //
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                MarkerOptions mOptions = new MarkerOptions();
                // 마커 타이틀
                mOptions.title("마커 좌표");
                Double latitude = point.latitude; // 위도
                Double longitude = point.longitude; // 경도
                // 마커의 스니펫(간단한 텍스트) 설정
                mOptions.snippet(latitude.toString() + ", " + longitude.toString());
                // LatLng: 위도 경도 쌍을 나타냄
                mOptions.position(new LatLng(latitude, longitude));
                // 마커(핀) 추가
                googleMap.addMarker(mOptions);
            }
        });

        // 버튼 이벤트
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();
                List<Address> addressList = null;

                if (editText.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "검색어를 입력하세요", Toast.LENGTH_SHORT).show();
                    editText.requestFocus();
                    return;
                }

                try {
                    // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
                    addressList = geocoder.getFromLocationName(
                            str, // 주소
                            10); // 최대 검색 결과 개수
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(addressList.get(0).toString());
                // 콤마를 기준으로 split
                String[] splitStr = addressList.get(0).toString().split(",");
                address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length() - 2); // 주소
                System.out.println(address);

                String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
                String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도
                System.out.println(latitude);
                System.out.println(longitude);

                // 좌표(위도, 경도) 생성
                LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                // 마커 생성

//              MarkerOptions mOptions2 = new MarkerOptions();
                mOptions2.title("search result");
                mOptions2.snippet(address);
                mOptions2.position(point);

                mMap.addMarker(mOptions2); // 마커 추가
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15)); // 해당 좌표로 화면 줌

                textView1.setText(address);
            }
        });

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(34, 124);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in korea"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        Button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", address);

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    //마커 클릭 리스너
    GoogleMap.OnMarkerClickListener markerClickListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            String markerId = marker.getId();
            //선택한 타겟위치
            LatLng location = marker.getPosition();
            Toast.makeText(MapsActivity.this, "마커 클릭 Marker ID : " + markerId + "(" + location.latitude + " " + location.longitude + ")", Toast.LENGTH_SHORT).show();
            return false;
        }
    };
}