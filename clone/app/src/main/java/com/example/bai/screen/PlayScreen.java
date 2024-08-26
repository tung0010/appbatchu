package com.example.bai.screen;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.bai.Database.Question;
import com.example.bai.Database.QuestionDatabase;
import com.example.bai.adapter.InputKeyWordadapter;
import com.example.bai.adapter.keywordadapter;
import com.example.bai.databinding.FragmentDialogAdvBinding;
import com.example.bai.databinding.PlayscreenBinding;
import com.example.bai.dialog.DialogCompleteFragment;
import com.example.bai.dialog.DialogSuggestFragment;
import com.example.bai.listener.keywordlistener;
import com.example.bai.utilities.Constants;

import com.example.bai.utilities.PreferenceManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PlayScreen extends AppCompatActivity implements DialogSuggestFragment.suggestListener, DialogCompleteFragment.okbtnlistener {
    PreferenceManager preferenceManager;
    PlayscreenBinding binding;
    ArrayList<Question> listquestions;//Chua danh sach cau hoi
    ArrayList<String> listinputkeyword;//Chua danh sach tu input
    ArrayList<String> listkeyword;//Danh sach tu khoa chinh xac
    InputKeyWordadapter rcvinputkeywordadapter;//Adapter cho recyclerview hien thi danh sach cac tu input
    keywordadapter rcvkeywordadapter;//Adapter cho recyclerview hien thi tu khoa da nhap
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//Chuoi alphabet
    int index = 0;//index la chi so cau hoi dang hien thi hien tai
    int ruby = 100;//ruby mac dinh la 100
    int countinput;//bien dem xem da nhap duoc bao nhieu tu
    int diemso = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PlayscreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //ham khoi tao
        init();
        CreateImageandKeyword();
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.suggest.setOnClickListener(view -> {

            DialogSuggestFragment dialogSuggestFragment = new DialogSuggestFragment();
//      dialogSuggestFragment.getActivity().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            Bundle bundle = new Bundle();
            String audiogoiy = listquestions.get(index).getAudiogoiy();
            bundle.putString("audiogoiy", audiogoiy);
            bundle.putInt("ruby", ruby);
            dialogSuggestFragment.setArguments(bundle);
            dialogSuggestFragment.show(getSupportFragmentManager(), null);


        });
    }

    private void CreateImageandKeyword() {
        //đổ dữ liệu ảnh gif câu hỏi hiện tại ra Gifview
        String uri = listquestions.get(index).getImage();
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        binding.imagegame.setImageResource(imageResource);
        //Mặc định hiển thị ban đầu cho recyclerview các chữ cái ban đầu trống nên ta để các phần tử trong listkeyword là ""
        for (int i = 0; i < listquestions.get(index).getKeyword().length(); i++) {
            listkeyword.add("");
        }
        //Đếm số lượng chữ cái đã nhập. Mặc định countinput là độ dài của listkeyword.
        countinput = listkeyword.size();
        //Thiết lập bộ nhập câu hỏi, gồm 20 ô nhập câu hỏi
        for (int i = 0; i < listquestions.get(index).getKeyword().length(); i++) {
            //lấy ra các chữ cái bắt buộc phải có trong bộ nhập và thêm nó vào listinputkeyword
            listinputkeyword.add(listquestions.get(index).getKeyword().substring(i, i + 1));
        }
        //Bộ nhập gồm 20 chữ cái, sau khi thêm các chữ cái bắt buộc có, bộ nhập vẫn chưa đủ chữ cái tối thiểu
        //Những chữ cái còn lại sẽ chọn ngẫu nhiên từ chuỗi String alphabet, và thêm vào bộ nhập
        for (int i = 0; i < 20; i++) {
            if (listinputkeyword.size() < 14) {
                Random rnd = new Random();
                char randomChar = alphabet.charAt(rnd.nextInt(alphabet.length()));
                listinputkeyword.add(String.valueOf(randomChar));
            } else {
                break;
            }

        }
        //Sau khi bộ nhập đã đủ 20 phần tử, ta sẽ random vị trí các chữ cái trong bộ nhập
        Collections.shuffle(listinputkeyword);
        //Đổ dữ liệu bộ nhập ra màn hình
        //Khoi tao adapter
        rcvinputkeywordadapter = new InputKeyWordadapter(listinputkeyword, this, new keywordlistener() {
            @Override //phuong thuc xu li su kien khi click vao cac item cua recyclerview nhap keyword
            public void onClickkeywordlistener(int position) {
                //vong for duyet cac vi tri phan tu rong trong recyclerview hien thi dap an
                for (int i = 0; i < listkeyword.size(); i++) {
                    //Neu trong listkeyword vẫn còn phần tử trống
                    if (listkeyword.get(i).equals("") == true) {
                        //Giảm 1 phần tử dếm countinput
                        countinput = countinput - 1;
                        //Set vị trí trống đó bằng giá trị của ô được click trong bộ nhập
                        listkeyword.set(i, listinputkeyword.get(position));
                        //Adapter lắng nghe thay đổi dữ liệu
                        rcvkeywordadapter.notifyDataSetChanged();
                        //Sau khi đổ dữ liệu xong, thoát khỏi vòng for
                        break;
                    }
                }

//                for (int i = 0; i < listinputkeyword.size(); i++) {
//                    if(listinputkeyword.get(position).equals("")!=true)
//                    {
//                        listinputkeyword.set(position,"");
//                        rcvinputkeywordadapter.notifyDataSetChanged();
//                    }
//
//                }
                //Kiểm tra xem người dùng đã nhập đến phần tử cuối cùng chưa
                if (countinput == 0) {
                //Nếu đã đến phần tử cuối cùng, kiểm tra xem đáp án đó có đúng không
                    checkans();
                }
            }
        });
        rcvkeywordadapter = new keywordadapter(listkeyword, this, new keywordlistener() {
            @Override
            public void onClickkeywordlistener(int position) {
                rcvkeywordadapter.animationcheck=false;
                if(listkeyword.get(position).equals("")==true)
                {}
                else
                {
                    listkeyword.set(position, "");
                    if (countinput < listkeyword.size()) {
                        countinput += 1;
                    }
                    rcvkeywordadapter.notifyDataSetChanged();

                }
            }
        });
        binding.rcvkeyword.setAdapter(rcvkeywordadapter);
        binding.rcvinputkeyword.setAdapter(rcvinputkeywordadapter);
        binding.rcvinputkeyword.setLayoutManager(new GridLayoutManager(getApplicationContext(),7));
        binding.rcvkeyword.setLayoutManager(new GridLayoutManager(getApplicationContext(),7));

        binding.rcvkeyword.setAdapter(rcvkeywordadapter);
        binding.rcvinputkeyword.setAdapter(rcvinputkeywordadapter);
    }



    private void createlistQuestions() {
        //Lay toan bo cau hoi co trong database
        listquestions.addAll(QuestionDatabase.getInstance(getApplicationContext()).questionDao().getAllQuestion());
        //Kiem tra xem bo cau hoi da co trong database chưa, nếu listquestion.size()=0 tức là lần đầu chạy ứng dụng thì ta
        //Nếu chưa có câu hỏi nào trong database, Khởi tạo từng đối tượng câu hỏi. Và thêm nó vào database. Đồng thời thêm vào listquestions

        if (listquestions.size() == 0) {
            //Tao cau hoi baixich
            Question baixich = new Question();
            baixich.setImage("@drawable/baixich");
            baixich.setKeyword("BAIXICH");
            baixich.setAudiogoiy("baixich");
            baixich.setDapan("BÀI XÍCH");
            //Thêm câu hỏi vào database
            insertQuestionDatabase(baixich);
            //Thêm câu hỏi vào listquestion
            listquestions.add(baixich);
            Question baphai = new Question();
            baphai.setImage("@drawable/baphai");
            baphai.setKeyword("BAPHAI");
            baphai.setAudiogoiy("baphai");
            baphai.setDapan("BA PHẢI");
            insertQuestionDatabase(baphai);
            listquestions.add(baphai);
            Question chanhcom = new Question();
            chanhcom.setImage("@drawable/chanhcom");
            chanhcom.setKeyword("CHANHCOM");
            chanhcom.setAudiogoiy("chanhcom");
            chanhcom.setDapan("CHANH CỐM");
            insertQuestionDatabase(chanhcom);
            listquestions.add(chanhcom);
        }
        //Random listquestion, vì mỗi lần chơi,thứ tự câu hỏi được hiển thị sẽ khác nhau. Nên cần random
        Collections.shuffle(listquestions);
    }


    private void init() {
        getData();
        listquestions = new ArrayList<>();
        listkeyword = new ArrayList<>();
        listinputkeyword = new ArrayList<>();
        createlistQuestions();


    }

    private void getData(){
        preferenceManager = new PreferenceManager(this);
        //lay du lieu ruby da luu trong may
        ruby = preferenceManager.getruby(Constants.KEY_RUBY);
        binding.ruby.setText(String.valueOf(ruby));
        binding.score.setText(String.valueOf(diemso));
    };
    int dem=0;
    //Hàm kiểm tra đáp án
    private void checkans() {
        //Nếu người dùng, đang sử dụng gợi ý. Dừng gợi ý lại để kiểm tra đáp án
        if (MainActivityScreen.mediaPlayer != null) {
            if (MainActivityScreen.mediaPlayer.isPlaying()) {
                MainActivityScreen.mediaPlayer.stop();
                MainActivityScreen.mediaPlayer.release();
                MainActivityScreen.mediaPlayer = null;
            }
        }
        //Stringbuilder ghép các chữ cái trong listkeyword thành 1 đáp án hoàn thiện
        StringBuilder sb = new StringBuilder();
        // Creating a string using append() method
        for (int i = 0; i < listkeyword.size(); i++) {
            sb.append(listkeyword.get(i));
        }
        //Sau khi được đáp án hoàn thiện, so sánh với đáp án của câu hỏi
        //Nếu đó là câu trả lời đúng
        if (sb.toString().equals(listquestions.get(index).getKeyword()) == true) {
            // tăng điểm số và ruby
            diemso += 5;
            ruby += 25;
            //Hiển thị dialog chúc mừng chiến thắng
            DialogCompleteFragment dialogCompleteFragment = new DialogCompleteFragment();
            //Chuyển dữ liệu điểm số ruby level đáp án vào Fragment Dialog
            Bundle b = new Bundle();
            b.putInt("diemso", diemso);
            b.putInt("level", index);
            b.putInt("ruby", ruby);
            b.putString("dapan", listquestions.get(index).getDapan());
            dialogCompleteFragment.setArguments(b);
            dialogCompleteFragment.show(getSupportFragmentManager(), null);
            //Sau khi được tăng điểm do trả lời đúng, kiểm tra xem điểm số đó đã phá vỡ kỷ lục chưa
            //Nếu lớn hơn kỷ lục cũ, thêm vào cơ sở dữ liệu
            if (diemso > preferenceManager.getkyluc(Constants.KEY_KYLUC)) {
                preferenceManager.putInt(Constants.KEY_KYLUC, diemso);
            }
            preferenceManager.putInt(Constants.KEY_RUBY, ruby);
            //Đổ dữ liệu ra Dialog
            binding.ruby.setText(String.valueOf(ruby));
            binding.score.setText(String.valueOf(diemso));
        } else {
            //Nếu người dùng trả lời sai, chạy animationcheck(), thông báo nhập sai
            rcvkeywordadapter.animationcheck=true;
            rcvkeywordadapter.notifyDataSetChanged();
            //dem++;
//            if(dem==3)
//            {
//                finish();
//            }
        }
    }

    //Hàm nextquestion, chuyển câu hỏi khác
    private void nextquestion() {
        //Kiểm tra xem câu hỏi hiện tại đã là câu hỏi cuối cùng chưa, nếu chưa, tăng index lên 1, ngược lại chuyển sang màn hình
        //thắng game
        if (index < listquestions.size() - 1) {
            index += 1;
            //Khởi tạo lại bộ câu hỏi mới
            listinputkeyword.clear();
            listkeyword.clear();
            CreateImageandKeyword();
        } else {
            startActivity(new Intent(getApplicationContext(), WingameScreen.class));
            finish();
        }
    }
//Phương thức xác nhận gợi ý
    @Override
    public void suggest(boolean yes, String audiogoiy) {
        if (yes == true) {
            //Neu ruby>0
            if (ruby > 0) {
                ruby = ruby - 25;
                preferenceManager.putInt(Constants.KEY_RUBY,ruby);
                binding.ruby.setText(String.valueOf(ruby));
                int resID = getResources().getIdentifier(audiogoiy, "raw", getPackageName());
                //Kiem tra xem co audio goi y nao dang chay khong, neu co thi dung no lai
                if (MainActivityScreen.mediaPlayer != null) {
                    if (MainActivityScreen.mediaPlayer.isPlaying()) {
                        MainActivityScreen.mediaPlayer.stop();
                        MainActivityScreen.mediaPlayer.release();
                        MainActivityScreen.mediaPlayer = null;
                    }
                }
                //Chay goi y moi
                MainActivityScreen.mediaPlayer = MediaPlayer.create(getApplicationContext(), resID);
                MainActivityScreen.mediaPlayer.start();


            }
            //Neu ruby =0;
            else {
                //Tao hoi thoai xem quang cao
                Dialog b = new Dialog(this);
                b.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                b.getWindow().setDimAmount(0.0f);
                FragmentDialogAdvBinding fragmentDialogAdvBinding = FragmentDialogAdvBinding.inflate(getLayoutInflater());
                b.setContentView(fragmentDialogAdvBinding.getRoot());
                b.setCancelable(true);
                b.setCanceledOnTouchOutside(false);
                fragmentDialogAdvBinding.no.setOnClickListener(view -> b.dismiss());
                fragmentDialogAdvBinding.yes.setOnClickListener(view -> {
                    startActivity(new Intent(getApplicationContext(), advertisementScreen.class));
                b.dismiss();});
                b.show();
            }
        }
    }


    @Override
    public void onClicknextquestion() {
        nextquestion();
    }
    //ham them cau hoi vao database
    public void insertQuestionDatabase(Question question) {
        QuestionDatabase.getInstance(getApplicationContext()).questionDao().insert(question);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Phuong thuc nay duoc goi khi nguoi choi vua chuyen tu man hinh xem quang cao tro ve
        ruby = preferenceManager.getruby(Constants.KEY_RUBY);
        binding.ruby.setText(String.valueOf(ruby));

    }
}
