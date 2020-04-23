package com.eighonet.pinball;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.DelegateAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.eighonet.pinball.objects.Angles;
import com.eighonet.pinball.objects.Ball;
import com.eighonet.pinball.objects.Borders;
import com.eighonet.pinball.objects.Checkers;
import com.eighonet.pinball.objects.Controllers;
import com.eighonet.pinball.objects.Reflectors;


public class Pinball implements Screen, ContactListener {
	
	private Ball ball;
	private Borders borders;
	private Angles angles;
	private Reflectors reflectors;
	private Checkers checkers;
	private Controllers controllers;
	
	World world;
	Box2DDebugRenderer rend;
	OrthographicCamera camera;
	private Viewport viewport;
	Body rect; 

	// Scores
	private float REF_ANG_COST = 50, REF_MAIN_COST = 100, REF_CHECKER_COST = 10;
	
	private int SCORES = 0, LIFES1 = 2;
	
	// Textures

	private Texture point1Texture;
	private Texture point1aTexture;
	private Texture point2Texture;
	private Texture point2aTexture;
	
	
	// Sprites

	private Sprite point1Sprite;
	private Sprite point1aSprite;
	private Sprite point2Sprite;
	private Sprite point2aSprite;
	
	
	
	
	
	
	// Render general
	private SpriteBatch batch, batch2, batch3;
	
	
	
	
	// Buttons
	Stage stage;
	Sprite left, right, menu;
	ImageButton leftB, rightB, menuButton;
	
	
	
	
	// Main elements declaration
	
	
	private Body jumperModel, controllerLModel, controllerRModel;
	
	
	private Vector2 jumperModelOrigin, controllerLModelOrigin, controllerRModelOrigin;

	
	private static final float ANGLE_WIDTH = (float) 0.8, VIEWPORT_WIDTH = 10,
	 JUMPER_WIDTH = (float)1, CONTROLLER_WIDTH = 1;
	
	boolean FLAG_DELETE = false, FLAG_POS = false;
	
	private BitmapFont font;
	public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
	
	private Game game;
	
	public Pinball(Game game) {
		this.game = game;
	}

	private void buttonsControl(Body controllerLModel, Body controllerRModel) {

	}
	
	@Override
	public void show() {
		
		float CAMERA_WIDTH =  8.5f;
		float CAMERA_HEIGHT = (float)CAMERA_WIDTH*((float)Gdx.graphics.getHeight()/Gdx.graphics.getWidth());
		
		world = new World(new Vector2(0, -10), true);
		
		world.setContactListener(this);
		
		camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		viewport = new FitViewport(CAMERA_WIDTH, CAMERA_HEIGHT, camera);
		rend = new Box2DDebugRenderer();
		
		
		
		ball = new Ball(world);
		borders = new Borders(world);
		angles = new Angles(world);
		reflectors = new Reflectors(world);
		checkers = new Checkers(world);
		controllers = new Controllers(world);
		
		ball.Create();
		borders.Create();
		angles.Create();
		reflectors.Create();
		checkers.Create();
		controllers.Create();
		
		createJumper();

		stage = new Stage();
		Sprite left = new Sprite(new Texture("leftControllerButton.png"));
		Sprite right = new Sprite(new Texture("rightControllerButton.png"));
		Sprite menu = new Sprite(new Texture("exitButton.jpg"));
		
		controllerLModel = controllers.GetModels()[0];
		controllerRModel = controllers.GetModels()[1];
		
		leftB = new ImageButton(
			    new TextureRegionDrawable(left),
			    new TextureRegionDrawable(left)
			);
		
		rightB = new ImageButton(
			    new TextureRegionDrawable(right),
			    new TextureRegionDrawable(right)
			);
		
		menuButton = new ImageButton(
			    new TextureRegionDrawable(menu),
			    new TextureRegionDrawable(menu)
			);
		
		
		leftB.addListener(new ClickListener() {
			
		    @Override
		    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		    	controllerLModel.setAngularVelocity(55);
		    	return true;
		    }
		    
		    @Override
		    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		    	controllerLModel.setAngularVelocity(0);
		    	controllerLModel.setTransform((float)-1.7,(float) -4, -(float)0.8636);
		    }
		});
		
		rightB.addListener(new ClickListener() {
			
		    @Override
		    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		    	controllerRModel.setAngularVelocity(-55);
		    	return true;
		    }
		    
		    @Override
		    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		    	controllerRModel.setAngularVelocity(0);
		    	controllerRModel.setTransform((float)0.9,(float) -3.8, (float)3.14);
		    }
		});
		
		menuButton.addListener(new ClickListener() {
			
		    @Override
		    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		    	System.out.println("MENU");
		    	batch.dispose();
		    	batch2.dispose();
		    	batch3.dispose();
				stage.dispose();
				game.setScreen(new Main(game));
		    	return true;
		    }
		    
		    @Override
		    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		    }
		});
		
		Gdx.input.setInputProcessor(stage);
		
		
		leftB.setBounds(	0, 0,
				Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
		rightB.setBounds(	Gdx.graphics.getWidth()/2, 0,
				Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
		menuButton.setPosition(Gdx.graphics.getWidth()/2 - menu.getWidth()/2, 
				Gdx.graphics.getHeight()/2 - 14f*menu.getHeight()/2);

		stage.addActor(leftB);
		stage.addActor(rightB);
		stage.addActor(menuButton);
		
		
		batch = new SpriteBatch();
		batch2 = new SpriteBatch();
		batch3 = new SpriteBatch();
		font = new BitmapFont();
	}
	
	private void createJumper() {
		// 0. Create a loader for the file saved from the editor.
		FileHandle file = Gdx.files.internal("data/border.json");
		BodyEditorLoader loader = new BodyEditorLoader(file);
		

		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.set((float)3.2,(float)-4.58);
		
		
		// Randomisation of the initial impulse
		Random random = new Random();
		int i = random.nextInt(3);
		
		FixtureDef fd = new FixtureDef();
		fd.density = 1;
		fd.friction = 0.5f;
		fd.restitution = 3f + i;

	
		// 3. Create a Body, as usual.
		jumperModel = world.createBody(bd);

		// 4. Create the body fixture automatically by using the loader.
		loader.attachFixture(jumperModel, "jumper", fd, (float) JUMPER_WIDTH);
		jumperModelOrigin = loader.getOrigin("jumper", JUMPER_WIDTH).cpy();
	}	
	

	private void drawScore(SpriteBatch batch2, float STRING) {
		batch2.begin();
		font.getData().setScale(3f);
		font.draw(batch2, "Score: " + STRING, 0.1f*Gdx.graphics.getWidth()/2, 5.9f*Gdx.graphics.getHeight()/6);
		batch2.end();
		
	}
	
	private void drawLifes(SpriteBatch batch2, float STRING) {
		batch2.begin();
		font.draw(batch2, "Lifes: " + STRING, 1.5f*Gdx.graphics.getWidth()/2, 5.9f*Gdx.graphics.getHeight()/6);
		batch2.end();
		
	}
	
	private void controllersControl(Body controllerLModel, Body controllerRModel) {
		
		if (controllerLModel.getAngle() >= 0) {
			controllerLModel.setAngularVelocity(0);
			controllerLModel.setTransform((float)-1.7,(float) -4,(float) 0);
    	}
		
		if (controllerRModel.getAngle() <= 2.3) {
			controllerRModel.setAngularVelocity(0);
			controllerRModel.setTransform((float)0.9,(float) -3.8, (float)2.3);
    	}
	}
	
	private void ballControl(Ball ball) {
		if (FLAG_DELETE == false) {
			
			if ((Math.abs(ball.GetModel().getLinearVelocity().x) + Math.abs(ball.GetModel().getLinearVelocity().y)) > 22) {
				ball.GetModel().setLinearVelocity(ball.GetModel().getLinearVelocity().x - ball.GetModel().getLinearVelocity().x/3,
						ball.GetModel().getLinearVelocity().y - ball.GetModel().getLinearVelocity().y/3);
			}
			
			ball.GetSprite()[0].draw(batch);
			if (ball.GetModel().getPosition().sub(ball.GetOrigin()).y < -4.65) {
				FLAG_DELETE = true;
				world.destroyBody(ball.GetModel());
				ball.GetModel().setUserData(null);
				LIFES1 -= 1;
				if (LIFES1 > 0) {
					ball = new Ball(world);
					ball.Create();
					ball.Assembly();
					FLAG_DELETE = false;
				} else {
					FileHandle file = Gdx.files.local("data/myfile.txt");
					file.writeString(" " + SCORES, true);

				}
			}
		}
	}
	

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(255, 255, 122, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		rend.render(world, camera.combined);
		world.step(1/60f, 4, 4);

		if (FLAG_DELETE == false) {
			ball.Assembly();
		}
		
		borders.Assembly();
		angles.Assembly();
		reflectors.Assembly();
		checkers.Assembly();
			
		controllersControl(controllers.GetModels()[0],controllers.GetModels()[1]);
			
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		
		 for (Sprite i:borders.GetSprite()) {
			 i.draw(batch);
		 }
		 
		 for (Sprite i:angles.GetSprite()) {
			 i.draw(batch);
		 }
		 
		 for (Sprite i:reflectors.GetSprite()) {
			 i.draw(batch);
		 }
		 
		 for (Sprite i:checkers.GetSprite()) {
			 i.draw(batch);
		 }
		 
		ballControl(ball);
	
		batch.end();
		
		drawScore(batch2, SCORES);
		drawLifes(batch3, LIFES1);
		
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	    camera.update();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

@Override
public void beginContact(Contact contact) {
	Fixture fa = contact.getFixtureA();
	Fixture fb = contact.getFixtureB();
	if ((fb.getBody().getUserData() == "0") || (fb.getBody().getUserData() == "1")
			|| (fb.getBody().getUserData() == "2") || (fb.getBody().getUserData() == "3")
			|| (fb.getBody().getUserData() == "4")|| (fb.getBody().getUserData() == "5")
			|| (fb.getBody().getUserData() == "6")) {
		SCORES += REF_CHECKER_COST;
	}
	if (fb.getBody().getUserData() == "angularReflector") {
		SCORES += REF_ANG_COST;
	}
	if (fb.getBody().getUserData() == "mainReflector") {
		SCORES += REF_MAIN_COST;
	}
}


	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}
	

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}

}