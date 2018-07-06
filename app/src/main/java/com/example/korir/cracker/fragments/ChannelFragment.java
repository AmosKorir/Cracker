package com.example.korir.cracker.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.korir.cracker.R;
import com.example.korir.cracker.adapters.VideoPostAdapter;
import com.example.korir.cracker.models.YoutubeModel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelFragment extends Fragment {
    public static  String CHANNELID="UC7MGnWilDcg157op0eKI1Tg";
    public static String GOOGLE_YOUTUBEAPI_KEY="AIzaSyC8vspMe1UjWj18bqtXr6AaguKDbgcM-64";
    public static String CHANNEL_GET_URL="https://www.googleapis.com/youtube/v3/search?key="+
            GOOGLE_YOUTUBEAPI_KEY+"&channelId="+CHANNELID+"&part=snippet,id&order=date&maxResults=20";


    public ChannelFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    VideoPostAdapter adapter=null;
    ArrayList<YoutubeModel> arrayList=new ArrayList<YoutubeModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_channel, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.channellist);
        new YoutubeAssync().execute();
        inList(arrayList);

        return view;
    }

    private void inList(ArrayList<YoutubeModel> arrayList) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        YoutubeModel model =new YoutubeModel();
        arrayList.add(model);
        adapter=new VideoPostAdapter(getActivity(),arrayList);
        recyclerView.setAdapter(adapter);
        int i=adapter.getItemCount();
        Toast.makeText(getActivity(), i+"", Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }



    //create a assyc class
    private class YoutubeAssync extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            HttpClient httpClient=new DefaultHttpClient();
            HttpGet httpGet=new HttpGet(CHANNEL_GET_URL);


            try {
                HttpResponse httpResponse=httpClient.execute(httpGet);
                HttpEntity httpEntity=httpResponse.getEntity();
                String json= EntityUtils.toString(httpEntity);

                return json;
            } catch (IOException e) {
                e.printStackTrace();

            }


            return null;
        }


        @Override
        protected void onPostExecute(String respone){
            super.onPostExecute(respone);
            if(respone!=null){
                try{
                    JSONObject jsonObject=new JSONObject(respone);
                    Log.e("reponce",jsonObject
                    .toString());
                    Toast.makeText(getActivity(), jsonObject
                            .toString(), Toast.LENGTH_LONG).show();
                    arrayList=parseVideoList(jsonObject);

                    int i=parseVideoList(jsonObject).size();
                    Toast.makeText(getActivity(), i+"", Toast.LENGTH_SHORT).show();

                }catch (Exception e){

                }

            }

        }

        private ArrayList<YoutubeModel> parseVideoList(JSONObject jsonObject) {
            ArrayList<YoutubeModel>mlist=new ArrayList<YoutubeModel>();


            if (jsonObject.has("items")){
                try{
                    JSONArray jsonArray=jsonObject.getJSONArray("items");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject json=jsonArray.getJSONObject(i);
                        if (json.has("id")){
                            JSONObject jsonID=json.getJSONObject("id");
                            if (jsonID.has("kind")){
                                if (jsonID.getString("kind").equals("youtube#searchResult")){
                                    //get the data from the snippet
                                    JSONObject jsonSnippet=json.getJSONObject("Snippet");
                                    String title=jsonSnippet.getString("title");
                                    String description=jsonSnippet.getString("description");
                                    String publishedAt=jsonSnippet.getString("publlishedAt");
                                    String thumbnail=jsonSnippet.getJSONObject("thumbnails").getJSONObject("high").getString("url");

                                    YoutubeModel model=new YoutubeModel();
                                    model.setTitle(title);
                                    model.setDescription(description);
                                    model.setPublishAt(publishedAt);
                                    model.setThumnail(thumbnail);
                                    mlist.add(model);
                                }
                            }
                        }

                    }
                }catch (Exception e){}
            }



            YoutubeModel model=new YoutubeModel();
            model.setTitle("mmmm");

                mlist.add(model);

            return mlist;
        }
    }
}