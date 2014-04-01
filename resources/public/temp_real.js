var renderer = null;
var scene = null;
var camera = null;
var cube = null;
var animating = false;

function onLoad()
{
    var container = document.getElementById("container");
    renderer = new THREE.WebGLRenderer({antialias: true});
    renderer.setSize(container.offsetWidth, container.offsetHeight);
    container.appendChild(renderer.domElement);

    scene = new THREE.Scene();
    camera = new THREE.PerspectiveCamera(45, container.offsetWidth /
					 container.offsetHeight, 1,
					 4000);
    camera.position.set(0, 0, 3);

    var light = new THREE.DirectionalLight(0xffffff, 1.5);
    light.position.set(0, 0, 1);
    scene.add(light);

    var mapUrl = "images/mr_wizard.002.jpg";
    var map = THREE.ImageUtils.loadTexture(mapUrl);

    var material = new THREE.MeshPhongMaterial({map: map});
    var geometry = new THREE.CubeGeometry(1, 1, 1);
    cube = new THREE.Mesh(geometry, material);
    
    cube.rotation.x = Math.PI / 5;
    cube.rotation.y = Math.PI / 5;
    scene.add(cube);

    addMouseHandler();
    run();
}

function run() 
{
    renderer.render(scene, camera);

    if (animating)
    {
	cube.rotation.y -= 0.01;
    }

    window.requestAnimationFrame(run)
}

function addMouseHandler()
{
    var dom = renderer.domElement;
    dom.addEventListener('mouseup', onMouseUp, false);
}

function onMouseUp(event)
{
    event.preventDefault();
    animating = (! animating);
}

