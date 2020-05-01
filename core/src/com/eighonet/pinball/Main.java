package com.eighonet.pinball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Main implements Screen {

	SpriteBatch batch;
	private Viewport viewport;
	private Camera camera;
	Stage stage;
	Game game;
	Texture img1, img2, img3, img4, img5, img6, img7; 
	ImageButton[] buttonImg = new ImageButton[7];
	Sprite[] buttonSprite = new Sprite[7];
	
	float curStageMargin = Gdx.graphics.getWidth();
	
	public Main(Game game) {
		this.game = game;
	}
	
	@Override
	public void show() {	
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new FitViewport(450, 800, camera);
		
	  	stage = new Stage();
	  	
		img1 = new Texture("MenuTitle.jpg"); // some texture
		img2 = new Texture("startButton.jpg");
		img3 = new Texture("startButtonTap.jpg");
		img4 = new Texture("recordsButton.jpg");
		img5 = new Texture("recordsButtonTap.jpg"); 
		img6 = new Texture("exitButton.jpg");
		img7 = new Texture("exitButtonTap.jpg");
		
		buttonSprite[0] = new Sprite(img1);
		buttonSprite[1] = new Sprite(img2);
		buttonSprite[2] = new Sprite(img3);
		buttonSprite[3] = new Sprite(img4);
		buttonSprite[4] = new Sprite(img5);
		buttonSprite[5] = new Sprite(img6);
		buttonSprite[6] = new Sprite(img7);
		
		buttonImg[0] = new ImageButton(
			    new TextureRegionDrawable(buttonSprite[0]),
			    new TextureRegionDrawable(buttonSprite[0])
			);
		for (int i = 1; i < 7; i += 2) {
			buttonImg[i] = new ImageButton(
				    new TextureRegionDrawable(buttonSprite[i]),
				    new TextureRegionDrawable(buttonSprite[i+1])
				);
		}

/*------------------- FIRST BUTTON -----------------------*/
		
			buttonImg[1].addListener(new ClickListener() {
		    @Override
		    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		    	
		    	System.out.println("PINBALL");
		    	return true;
		    }
		    @Override
		    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		    	batch.dispose();
				stage.dispose();
		    	game.setScreen(new Pinball(game));
		    	System.out.println("c");
		    }
		});

/*------------------- SECOND BUTTON -----------------------*/
			
			buttonImg[3].addListener(new ClickListener() {
			    @Override
			    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			    	
			    	System.out.println("REC");
			    	return true;
			    }
			    @Override
			    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			    	batch.dispose();
					stage.dispose();
			    	game.setScreen(new Records(game));
			    	System.out.println("c");
			    }
			});
			
			
/*------------------- THIRD BUTTON -----------------------*/
			
			buttonImg[5].addListener(new ClickListener() {
			    @Override
			    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			    	
			    	
			    	return true;
			    }
			    @Override
			    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			    	Gdx.app.exit();
			    	System.out.println("c");
			    }
			});
			
			
			
		
		Gdx.input.setInputProcessor(stage);
		System.out.println(Gdx.graphics.getWidth()/2);
		System.out.println(buttonSprite[1].getWidth()/2);
		
		buttonImg[0].setPosition(Gdx.graphics.getWidth()/2 - buttonSprite[0].getWidth()/2, 
		Gdx.graphics.getHeight()/2 + 5*buttonSprite[0].getHeight()/2);
		
		stage.addActor(buttonImg[0]);
		
//		READING / WRITING TO FILE		
//		FileHandle file = Gdx.files.local("data/myfile.txt");
//		file.writeString("My god, it's full of stars", true);
//		System.out.println(file.readString());
		

		for (int i = 1; i < 7; i += 2) {
			buttonImg[i].setPosition(Gdx.graphics.getWidth()/2 - buttonSprite[i].getWidth()/2, 
					Gdx.graphics.getHeight()/2 - i*buttonSprite[i].getHeight() + 3*buttonSprite[i].getHeight()/2);
			stage.addActor(buttonImg[i]);
		
		}
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		stage.draw();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
        viewport.update(width, height);
        
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