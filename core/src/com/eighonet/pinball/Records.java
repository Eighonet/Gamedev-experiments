package com.eighonet.pinball;

import java.util.Collections;
import java.util.Vector;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Records implements Screen {

	SpriteBatch batch, batch2;
	private Viewport viewport;
	private Camera camera;
	Stage stage;
	Game game;
	ImageButton menuButton;
	private BitmapFont font;
	
	public Records(Game game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new FitViewport(450, 800, camera);
	  	stage = new Stage();
	  	Sprite menu = new Sprite(new Texture("exitButton.jpg"));
	  	
		menuButton = new ImageButton(
			    new TextureRegionDrawable(menu),
			    new TextureRegionDrawable(menu)
			);
		
		
	menuButton.addListener(new ClickListener() {
			
		    @Override
		    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		    	System.out.println("MENU");
		    	batch.dispose();
		    	batch2.dispose();
		    	stage.dispose();
				game.setScreen(new Main(game));
		    	return true;
		    }
		    
		    @Override
		    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		    }
		});
		
	Gdx.input.setInputProcessor(stage);
	
	menuButton.setPosition(Gdx.graphics.getWidth()/2 - menu.getWidth()/2, 
			Gdx.graphics.getHeight()/2 - 14f*menu.getHeight()/2);
	stage.addActor(menuButton);
	
	batch = new SpriteBatch();
	batch2 = new SpriteBatch();
	
	font = new BitmapFont();
	}
	
	private void displayTable() {
		batch2.begin();
		FileHandle file = Gdx.files.local("data/myfile.txt");
		font.getData().setScale(3f);
		Vector < Integer > a  = new Vector< Integer >();
		String tmp = "";
		String b = file.readString() + " ";
		
		for (int i = 0; i < b.length(); i++) {
			if (b.charAt(i) != ' ') {
				tmp += b.charAt(i);
			} else {
				a.add(Integer.parseInt(tmp));
				tmp = "";
			}
		}
		System.out.println(a.size() + " ");

		Collections.sort(a);

		tmp = "";
		for (int i = a.size() - 1; i >= a.size() - 6; --i) {
			tmp += (a.elementAt(i) + "\n") ;
		}
		font.draw(batch2, tmp, 0.1f*Gdx.graphics.getWidth()/2, 5.9f*Gdx.graphics.getHeight()/6);
		batch2.end();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		displayTable();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
