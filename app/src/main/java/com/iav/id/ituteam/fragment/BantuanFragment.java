package com.iav.id.ituteam.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.database.DatabaseReference;
import com.iav.id.ituteam.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BantuanFragment extends Fragment{

    RecyclerView recyclerView;
    EditText editText;
    RelativeLayout addBtn;
    DatabaseReference ref;
    ImageView fab_img;
//    FirebaseRecyclerAdapter<ChatMessage,chat_rec> adapter;
    Boolean flagFab = true;

//    private AIService aiService;
    public BantuanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_bantuan, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        editText = (EditText) view.findViewById(R.id.editText);
        addBtn = (RelativeLayout)view.findViewById(R.id.addBtn);
        fab_img = (ImageView) view.findViewById(R.id.fab_img);

//        recyclerView.setHasFixedSize(true);
//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setStackFromEnd(true);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        ref = FirebaseDatabase.getInstance().getReference();
//        ref.keepSynced(true);
//
//        final AIConfiguration config = new AIConfiguration("0c01e159babc4349b38eca698bd2f107",
//                AIConfiguration.SupportedLanguages.English,
//                AIConfiguration.RecognitionEngine.System);
//
//        aiService = AIService.getService(getActivity(), config);
//        aiService.setListener(this);
//
//        final AIDataService aiDataService = new AIDataService(config);
//
//        final AIRequest aiRequest = new AIRequest();
//
//
//
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String message = editText.getText().toString().trim();
//
//                if (!message.equals("")) {
//
//                    ChatMessage chatMessage = new ChatMessage(message, "user");
//                    ref.child("chat").push().setValue(chatMessage);
//
//                    aiRequest.setQuery(message);
//                    new AsyncTask<AIRequest,Void,AIResponse>(){
//
//                        @Override
//                        protected AIResponse doInBackground(AIRequest... aiRequests) {
//                            final AIRequest request = aiRequests[0];
//                            try {
//                                final AIResponse response = aiDataService.request(aiRequest);
//                                return response;
//                            } catch (AIServiceException e) {
//                            }
//                            return null;
//                        }
//                        @Override
//                        protected void onPostExecute(AIResponse response) {
//                            if (response != null) {
//
//                                Result result = response.getResult();
//                                String reply = result.getFulfillment().getSpeech();
//                                ChatMessage chatMessage = new ChatMessage(reply, "bot");
//                                ref.child("chat").push().setValue(chatMessage);
//                            }
//                        }
//                    }.execute(aiRequest);
//                }
//                else {
//                    aiService.startListening();
//                }
//
//                editText.setText("");
//
//            }
//        });
//
//
//
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
////                Bitmap img = BitmapFactory.decodeResource(getResources(),R.drawable.ic_send_white_24dp);
////                Bitmap img1 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_mic_white_24dp);
////
////
////                if (s.toString().trim().length()!=0 && flagFab){
////                    ImageViewAnimatedChange(getActivity(),fab_img,img);
////                    flagFab=false;
////
////                }
////                else if (s.toString().trim().length()==0){
////                    ImageViewAnimatedChange(getActivity(),fab_img,img1);
////                    flagFab=true;
////
////                }
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

//        adapter = new FirebaseRecyclerAdapter
//
//        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onItemRangeInserted(int positionStart, int itemCount) {
//                super.onItemRangeInserted(positionStart, itemCount);
//
//                int msgCount = adapter.getItemCount();
//                int lastVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
//
//                if (lastVisiblePosition == -1 ||
//                        (positionStart >= (msgCount - 1) &&
//                                lastVisiblePosition == (positionStart - 1))) {
//                    recyclerView.scrollToPosition(positionStart);
//
//                }
//
//            }
//        });
//
//        recyclerView.setAdapter(adapter);

        return view;
    }

//    public void ImageViewAnimatedChange(Context c, final ImageView v, final Bitmap new_image) {
//        final Animation anim_out = AnimationUtils.loadAnimation(c, R.anim.zoom_out);
//        final Animation anim_in  = AnimationUtils.loadAnimation(c, R.anim.zoom_in);
//        anim_out.setAnimationListener(new Animation.AnimationListener()
//        {
//            @Override public void onAnimationStart(Animation animation) {}
//            @Override public void onAnimationRepeat(Animation animation) {}
//            @Override public void onAnimationEnd(Animation animation)
//            {
//                v.setImageBitmap(new_image);
//                anim_in.setAnimationListener(new Animation.AnimationListener() {
//                    @Override public void onAnimationStart(Animation animation) {}
//                    @Override public void onAnimationRepeat(Animation animation) {}
//                    @Override public void onAnimationEnd(Animation animation) {}
//                });
//                v.startAnimation(anim_in);
//            }
//        });
//        v.startAnimation(anim_out);
//    }

//
//    @Override
//    public void onResult(AIResponse response) {
//
//        Result result = response.getResult();
//
//        String message = result.getResolvedQuery();
//        ChatMessage chatMessage0 = new ChatMessage(message, "user");
//        ref.child("chat").push().setValue(chatMessage0);
//
//
//        String reply = result.getFulfillment().getSpeech();
//        ChatMessage chatMessage = new ChatMessage(reply, "bot");
//        ref.child("chat").push().setValue(chatMessage);
//
//
//    }

//    @Override
//    public void onError(AIError error) {
//
//    }
//
//    @Override
//    public void onAudioLevel(float level) {
//
//    }
//
//    @Override
//    public void onListeningStarted() {
//
//    }
//
//    @Override
//    public void onListeningCanceled() {
//
//    }
//
//    @Override
//    public void onListeningFinished() {
//
//    }
}
